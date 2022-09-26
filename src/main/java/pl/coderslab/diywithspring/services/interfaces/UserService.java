package pl.coderslab.diywithspring.services.interfaces;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.Role;
import pl.coderslab.diywithspring.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


public interface UserService {

    User findByUserName(String username);
    void saveUser(User user);
    void saveAdmin(User user);
    User findUserById(Long id);

    List<User> findAllUsersByRoles(Role role);

    void deleteUserRoleByUserId(Long userId);

    void deleteUserById(Long userId);

    Set<String> getUserNameList();
}
