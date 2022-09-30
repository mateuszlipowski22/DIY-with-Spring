package pl.coderslab.diywithspring.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.RoleService;
import pl.coderslab.diywithspring.services.interfaces.UserService;
import pl.coderslab.diywithspring.web.storage.UserStorage;

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


    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
//        return UserStorage.getInstance().getUsers();
        return userService.findAllUsersByRoles(roleService.findByName("ROLE_USER")).stream().map(User::getUsername).collect(Collectors.toSet());
    }
}
