package ro.adi.shop.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.adi.shop.auth.exception.InvalidRequestException;
import ro.adi.shop.auth.exception.RetriesExceededException;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ro.adi.shop.security.DeviceActivity.MAX_RETRIES;

@Component
@Order(0)
public class DeviceActivityFilter extends OncePerRequestFilter {

    private final Map<String, DeviceActivity> deviceActivityByRemoteAddress = new HashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        var remoteAddr = request.getRemoteAddr();
        checkIfDeviceHasValidRequest(request);
        createOrUpdateDeviceActivityRegistration(remoteAddr);
        var isReset = isTheRetriesForDeviceReset(remoteAddr);
        if (!isReset) checkIfDeviceIsValidToCallTheApi(remoteAddr);
        else createDeviceActivity(remoteAddr);
        chain.doFilter(request, response);
    }

    private void createOrUpdateDeviceActivityRegistration(String address) {

        var deviceAlreadyRegistered = deviceActivityByRemoteAddress.containsKey(address);
        if (deviceAlreadyRegistered) {
            updateDeviceActivity(address);
            return;
        }
        createDeviceActivity(address);
    }

    private void updateDeviceActivity(String address) {
        var deviceActivity = deviceActivityByRemoteAddress.get(address);
        var retries = (byte) (deviceActivity.getRetries() + 1);
        deviceActivity.setRetries(retries);
    }

    private void createDeviceActivity(String address) {
        var newDeviceActivity = new DeviceActivity();
        newDeviceActivity.setCreatedFirstRequest(Instant.now());
        newDeviceActivity.setRetries((byte) 1);
        deviceActivityByRemoteAddress.put(address, newDeviceActivity);
    }

    private void checkIfDeviceIsValidToCallTheApi(String remoteAddress) {

        var deviceActivity = this.deviceActivityByRemoteAddress.get(remoteAddress);
        var retries = deviceActivity.getRetries();
        if (retries > MAX_RETRIES) throw new RetriesExceededException("Exceeded retries!");
    }

    private boolean isTheRetriesForDeviceReset(String remoteAddress) {

        var deviceActivity = this.deviceActivityByRemoteAddress.get(remoteAddress);
        var startMakingRequests = deviceActivity.getCreatedFirstRequest();
        var timeElapsed = Duration.between(startMakingRequests, Instant.now()).toMinutes();
        var needToBeReset = timeElapsed > 60;
        if (needToBeReset) resetDeviceActivity(remoteAddress);
        return needToBeReset;
    }

    private void resetDeviceActivity(String remoteAddress) {

        this.deviceActivityByRemoteAddress.remove(remoteAddress);
    }

    private void checkIfDeviceHasValidRequest(ServletRequest request) {

        var isRemoteAddressPresentInRequest = Optional.ofNullable(request.getRemoteAddr()).isPresent();
        if (!isRemoteAddressPresentInRequest)
            throw new InvalidRequestException("Request is invalid, please try to refresh the page");
    }
}
