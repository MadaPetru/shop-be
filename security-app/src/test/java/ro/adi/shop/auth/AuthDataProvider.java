package ro.adi.shop.auth;

import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ro.adi.shop.auth.dto.request.UserLoginRequest;

@UtilityClass
public class AuthDataProvider {

    public final static String USERNAME = "USER";
    public final static String PASSWORD = "PASSWORD";

    public static UserLoginRequest createUserLoginRequestMock() {

        return UserLoginRequest
                .builder()
                .password(PASSWORD)
                .username(USERNAME)
                .build();
    }

    public static Authentication createAuthenticationMock() {

        return new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD);
    }

    public static TestingAuthenticationToken createTestingAuthenticationTokenMock() {

        return new TestingAuthenticationToken(new Object(), new Object());
    }
}
