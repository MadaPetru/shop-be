package ro.adi.shop.auth.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import ro.adi.shop.security.JwtTokenProvider;

import static org.mockito.Mockito.*;
import static ro.adi.shop.auth.AuthDataProvider.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    AuthServiceImpl classUnderTest;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Test
    void login_shouldBeSuccessfully() {
        var request = createUserLoginRequestMock();
        var usernamePasswordAuthenticationTokenMock = createAuthenticationMock();
        var expectedAuthentication = createTestingAuthenticationTokenMock();
        when(authenticationManager.authenticate(usernamePasswordAuthenticationTokenMock)).thenReturn(expectedAuthentication);

        classUnderTest.login(request);

        verify(jwtTokenProvider, times(1)).generateToken(expectedAuthentication);
    }

}