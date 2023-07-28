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

import static ro.adi.shop.security.DeviceActivityRegistration.MAX_RETRIES;

@Component
@Order(0)
public class DeviceActivityFilter extends OncePerRequestFilter {

    private final Map<String, DeviceActivityRegistration> userActivityRegistrationByRemoteAddress = new HashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        var remoteAddr = request.getRemoteAddr();
        checkIfDeviceHasValidRequest(request);
        var deviceActivity = createOrUpdateDeviceActivityRegistration(remoteAddr);
        resetIfNeededTheRetriesForDevice(deviceActivity, remoteAddr);
        checkIfDeviceIsValidToCallTheApi(deviceActivity);
        chain.doFilter(request, response);
    }

    private DeviceActivityRegistration createOrUpdateDeviceActivityRegistration(String address) {

        var deviceAlreadyRegistered = userActivityRegistrationByRemoteAddress.containsKey(address);
        if (deviceAlreadyRegistered) {
            var deviceActivity = userActivityRegistrationByRemoteAddress.get(address);
            var retries = (byte) (deviceActivity.getRetries() + 1);
            deviceActivity.setRetries(retries);
            return deviceActivity;
        }
        var newDeviceActivity = new DeviceActivityRegistration();
        newDeviceActivity.setCreatedFirstRequest(Instant.now());
        newDeviceActivity.setRetries((byte) 1);
        userActivityRegistrationByRemoteAddress.put(address, newDeviceActivity);
        return newDeviceActivity;
    }

    private void checkIfDeviceIsValidToCallTheApi(DeviceActivityRegistration deviceActivity) {

        var retries = deviceActivity.getRetries();
        if (retries > MAX_RETRIES) throw new RetriesExceededException("Exceeded retries!");
    }

    private void resetIfNeededTheRetriesForDevice(DeviceActivityRegistration deviceActivity, String remoteAddress) {

        var startMakingRequests = deviceActivity.getCreatedFirstRequest();
        var timeElapsed = Duration.between(Instant.now(), startMakingRequests).toMinutes();
        if (timeElapsed > 60) resetDeviceActivity(remoteAddress);
    }

    private void resetDeviceActivity(String remoteAddress) {

        this.userActivityRegistrationByRemoteAddress.remove(remoteAddress);
    }

    private void checkIfDeviceHasValidRequest(ServletRequest request) {

        var isRemoteAddressPresentInRequest = Optional.ofNullable(request.getRemoteAddr()).isPresent();
        if (!isRemoteAddressPresentInRequest)
            throw new InvalidRequestException("Request is invalid, please try to refresh the page");
    }
}
