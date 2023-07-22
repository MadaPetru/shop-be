package ro.adi.shop.users.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.adi.shop.users.jpa.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
