package ro.adi.shop.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.shop.auth.AuthController;
import ro.adi.shop.auth.dto.GrantedRoleDto;
import ro.adi.shop.auth.dto.request.UserLoginRequest;
import ro.adi.shop.auth.dto.response.UserLoginResponse;
import ro.adi.shop.auth.service.AuthService;
import ro.adi.shop.users.UserConverter;
import ro.adi.shop.users.service.UserServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final UserServiceImpl userService;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {

        var jwt = authService.login(request);
        var roles = getGrantedRoles(request);
        return UserConverter.convertToUserLoginResponse(jwt, roles);
    }

    private Set<GrantedRoleDto> getGrantedRoles(UserLoginRequest request) {
        return userService.loadUserByUsername(request.getUsername())
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(GrantedRoleDto::valueOf)
                .collect(Collectors.toSet());
    }
}
