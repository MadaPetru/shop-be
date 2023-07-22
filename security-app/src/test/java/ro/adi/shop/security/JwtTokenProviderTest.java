package ro.adi.shop.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.util.ReflectionTestUtils;
import ro.adi.shop.users.jpa.entity.GrantedRole;

import static org.junit.jupiter.api.Assertions.*;
import static ro.adi.shop.auth.AuthDataProvider.USERNAME;
import static ro.adi.shop.auth.AuthDataProvider.createAuthenticationMock;


@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

    @InjectMocks
    JwtTokenProvider classUnderTest;

    @Test
    void generateToken_shouldReturnCorrectToken() {
        mockValues();
        var authenticationMock = createAuthenticationMock();

        var actualToken = classUnderTest.generateToken(authenticationMock);

        assertNotNull(actualToken);
    }

    @Test
    void getUsername_shouldReturnUsername() {
        var token = generateJwtToken();

        var actualUsername = classUnderTest.getUsername(token);

        assertEquals(USERNAME, actualUsername);
    }

    @Test
    void validateToken_shouldReturnTrue() {
        var token = generateJwtToken();

        var isValid = classUnderTest.validateToken(token);

        assertTrue(isValid);
    }

    @Test
    void validateToken_shouldThrowMalformedJwtException() {
        mockValues();
        var token = " s";

        var isValid = classUnderTest.validateToken(token);

        assertFalse(isValid);
    }

    @Test
    void validateToken_shouldThrowExpiredJwtException() {
        var token = generateExpiredJwtToken();

        var isValid = classUnderTest.validateToken(token);

        assertFalse(isValid);
    }

    @Test
    void validateToken_shouldThrowSignatureException() {
        mockValues();
        var token = "eyJhbGciOiJIUzI1NiJ9..signature";

        var isValid = classUnderTest.validateToken(token);

        assertFalse(isValid);
    }

    @Test
    void validateToken_shouldThrowIllegalArgumentException() {
        var token = "";

        var isValid = classUnderTest.validateToken(token);

        assertFalse(isValid);
    }

    private String generateExpiredJwtToken() {

        mockValuesForExpiredToken();
        var authenticationMock = new UsernamePasswordAuthenticationToken(USERNAME, GrantedRole.NORMAL_USER);
        return classUnderTest.generateToken(authenticationMock);
    }

    private String generateJwtToken() {

        mockValues();
        var authenticationMock = new UsernamePasswordAuthenticationToken(USERNAME, GrantedRole.NORMAL_USER);
        return classUnderTest.generateToken(authenticationMock);
    }

    private void mockValues() {

        ReflectionTestUtils.setField(classUnderTest, "jwtSecret", "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb");
        ReflectionTestUtils.setField(classUnderTest, "jwtExpirationDate", 3600000L);
    }

    private void mockValuesForExpiredToken() {

        ReflectionTestUtils.setField(classUnderTest, "jwtSecret", "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb");
        ReflectionTestUtils.setField(classUnderTest, "jwtExpirationDate", 0L);
    }
}