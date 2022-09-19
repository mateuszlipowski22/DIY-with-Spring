package pl.coderslab.diywithspring.services;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.User;


public interface UserService {

    User findByUserName(String username);
    void saveUser(User user);
    void saveAdmin(User user);
    User findUserById(Long id);
}
