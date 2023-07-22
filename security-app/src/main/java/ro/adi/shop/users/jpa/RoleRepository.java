package ro.adi.shop.users.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.adi.shop.users.jpa.entity.GrantedRole;
import ro.adi.shop.users.jpa.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByGrantedRole(GrantedRole grantedRole);
}
