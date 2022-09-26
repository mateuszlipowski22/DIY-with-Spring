package pl.coderslab.diywithspring.services.interfaces;

import pl.coderslab.diywithspring.models.Role;

public interface RoleService {

    Role findByName(String name);

}
