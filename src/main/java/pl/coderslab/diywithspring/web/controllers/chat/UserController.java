package pl.coderslab.diywithspring.web.controllers.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.RoleService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {

        System.out.println(userService.getCurrentLoggedUserNameList());
        System.out.println(userService.findAllUsersByRoles(roleService.findByName("ROLE_USER")).stream().map(User::getUsername).collect(Collectors.toSet()));
        return userService.findAllUsersByRoles(roleService.findByName("ROLE_USER")).stream().map(User::getUsername).collect(Collectors.toSet());
    }

    @GetMapping("/fetchAllLoggedUsers")
    public Set<String> fetchAllLoggedUsers() {
        return userService.getCurrentLoggedUserNameList();
    }
}
