package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.diywithspring.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);

}
