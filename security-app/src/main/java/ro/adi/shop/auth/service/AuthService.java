package ro.adi.shop.auth.service;

import ro.adi.shop.auth.dto.request.UserLoginRequest;

public interface AuthService {

    String login(UserLoginRequest request);
}
