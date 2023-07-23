package ro.adi.shop.users.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.shop.BaseEntity;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user", schema = "public")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_sequence", allocationSize = 32)
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
