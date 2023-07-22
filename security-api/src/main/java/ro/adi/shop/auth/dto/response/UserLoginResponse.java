package ro.adi.shop.auth.dto.response;

import lombok.Builder;
import lombok.Data;
import ro.adi.shop.auth.dto.GrantedRoleDto;

import java.util.Set;

@Builder
@Data
public class UserLoginResponse {

    private String jwt;
    private Set<GrantedRoleDto> roles;
}
