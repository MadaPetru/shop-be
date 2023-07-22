package ro.adi.shop.users.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.adi.shop.users.jpa.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static ro.adi.shop.users.UserDataProvider.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl classUnderTest;
    @Mock
    UserRepository userRepository;

    @Test
    void loadUserBuUsername_shouldReturnTheUser() {
        var expectedResult = createExpectedUserDetails();
        when(userRepository.findByUsername(USERNAME)).thenReturn(createOptionalOfUser());

        var actualResult = classUnderTest.loadUserByUsername(USERNAME);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void loadUserBuUsername_shouldThrowUsernameNotFoundException() {
        when(userRepository.findByUsername(USERNAME)).thenThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class, () -> classUnderTest.loadUserByUsername(USERNAME));
    }
}