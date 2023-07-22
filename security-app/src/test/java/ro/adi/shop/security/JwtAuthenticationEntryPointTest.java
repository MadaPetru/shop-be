package ro.adi.shop.security;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static ro.adi.shop.security.SecurityDataProvider.EXPECTED_ERROR_MESSAGE;
import static ro.adi.shop.security.SecurityDataProvider.createAccountExpiredException;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationEntryPointTest {

    @InjectMocks
    JwtAuthenticationEntryPoint classUnderTest;

    @Mock
    MockHttpServletResponse mockHttpServletResponse;

    @Mock
    MockHttpServletRequest mockHttpServletRequest;

    @Test
    void commence_shouldCallSendErrorMethod() throws IOException {
        classUnderTest.commence(mockHttpServletRequest, mockHttpServletResponse, createAccountExpiredException());

        verify(mockHttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPECTED_ERROR_MESSAGE);
    }

}