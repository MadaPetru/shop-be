package ro.adi.shop.users.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    @SequenceGenerator(name = "role_gen", sequenceName = "role_sequence", allocationSize = 32, initialValue = 1)
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private GrantedRole grantedRole;
}
