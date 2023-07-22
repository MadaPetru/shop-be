package ro.adi.shop.users;

import lombok.experimental.UtilityClass;
import ro.adi.shop.users.jpa.entity.GrantedRole;
import ro.adi.shop.users.jpa.entity.Role;

@UtilityClass
public class RoleDataProvider {

    public final static Long ID = 1L;
    public final static GrantedRole ROLE = GrantedRole.NORMAL_USER;

    public static Role createRole() {

        var mock = new Role();
        mock.setGrantedRole(ROLE);
        mock.setId(ID);
        return mock;
    }
}
