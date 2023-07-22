package ro.adi.shop.security;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ro.adi.shop.security.SecurityDataProvider.*;
import static ro.adi.shop.users.UserDataProvider.USERNAME;
import static ro.adi.shop.users.UserDataProvider.createExpectedUserDetails;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @InjectMocks
    JwtAuthenticationFilter classUnderTest;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    UserDetailsService userDetailsService;

    @Test
    void doFilterInternal_shouldSkipIfNoTokenProvidedToNextFilter() throws ServletException, IOException {
        classUnderTest.doFilterInternal(createMockHttpServletRequest(), createMockHttpServletResponse(), createMockFilterChain());

        verify(jwtTokenProvider, never()).getUsername(anyString());
        verify(userDetailsService, never()).loadUserByUsername(anyString());
    }

    @Test
    void doFilterInternal_shouldSetSecurityContextWithUserForValidToken() throws ServletException, IOException {
        var mockHttpServletRequest = createMockHttpServletRequest();
        mockHttpServletRequest.addHeader("Authorization", "Bearer " + EXPECTED_MOCK_TOKEN);
        when(jwtTokenProvider.validateToken(EXPECTED_MOCK_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getUsername(EXPECTED_MOCK_TOKEN)).thenReturn(USERNAME);
        when(userDetailsService.loadUserByUsername(USERNAME)).thenReturn(createExpectedUserDetails());

        classUnderTest.doFilterInternal(mockHttpServletRequest, createMockHttpServletResponse(), createMockFilterChain());

        verify(jwtTokenProvider).getUsername(EXPECTED_MOCK_TOKEN);
        verify(jwtTokenProvider).validateToken(EXPECTED_MOCK_TOKEN);
        verify(userDetailsService).loadUserByUsername(USERNAME);
    }
}