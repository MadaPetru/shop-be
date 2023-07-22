package ro.adi.shop.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.shop.users.jpa.UserRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        var roles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getGrantedRole().name()))
                .collect(Collectors.toSet());
        return User.builder()
                .password(user.getPassword())
                .authorities(roles)
                .username(username)
                .build();
    }
}
