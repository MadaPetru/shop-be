package ro.adi.shop.security;

import lombok.experimental.UtilityClass;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;

@UtilityClass
public class SecurityDataProvider {

    public final static String EXPECTED_MOCK_TOKEN = "mock token";
    public final static String EXPECTED_ERROR_MESSAGE = "Expected error message";

    public static MockHttpServletRequest createMockHttpServletRequest() {

        return new MockHttpServletRequest();
    }

    public static MockHttpServletResponse createMockHttpServletResponse() {

        return new MockHttpServletResponse();
    }

    public static MockFilterChain createMockFilterChain() {

        return new MockFilterChain();
    }

    public static AccountExpiredException createAccountExpiredException() {

        return new AccountExpiredException(EXPECTED_ERROR_MESSAGE);
    }
}
