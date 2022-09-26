package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.Role;
import pl.coderslab.diywithspring.repositories.RoleRepository;
import pl.coderslab.diywithspring.services.interfaces.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
