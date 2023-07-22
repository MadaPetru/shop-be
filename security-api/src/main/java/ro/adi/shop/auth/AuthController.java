package ro.adi.shop.auth;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.adi.shop.auth.dto.request.UserLoginRequest;
import ro.adi.shop.auth.dto.response.UserLoginResponse;

@RequestMapping("/users")
@Validated
public interface AuthController {

    @PostMapping("/login")
    UserLoginResponse login(@RequestBody UserLoginRequest request);
}
