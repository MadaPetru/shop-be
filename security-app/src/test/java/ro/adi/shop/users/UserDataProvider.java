package ro.adi.shop.users;

import lombok.experimental.UtilityClass;
import ro.adi.shop.users.jpa.entity.User;

import java.util.Optional;
import java.util.Set;

@UtilityClass
public class UserDataProvider {

    public final static String USERNAME = "bo$$u";
    public final static String PASSWORD = "0000";
    public final static Long ID = 1L;

    public static Optional<User> createOptionalOfUser() {

        return Optional.of(getUserMock());
    }

    public static org.springframework.security.core.userdetails.UserDetails createExpectedUserDetails() {

        return org.springframework.security.core.userdetails.User
                .builder()
                .password(PASSWORD)
                .username(USERNAME)
                .authorities(String.valueOf(RoleDataProvider.ROLE))
                .build();
    }

    private User getUserMock() {

        var mock = new User();
        mock.setId(ID);
        mock.setPassword(PASSWORD);
        mock.setUsername(USERNAME);
        mock.setRoles(Set.of(RoleDataProvider.createRole()));
        return mock;
    }
}
