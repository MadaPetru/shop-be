package ro.adi.shop.users;

import lombok.experimental.UtilityClass;
import ro.adi.shop.auth.dto.GrantedRoleDto;
import ro.adi.shop.auth.dto.response.UserLoginResponse;

import java.util.Set;

@UtilityClass
public class UserConverter {

    public UserLoginResponse convertToUserLoginResponse(String jwt, Set<GrantedRoleDto> roles) {
        return UserLoginResponse.builder()
                .jwt(jwt)
                .roles(roles)
                .build();
    }
}
