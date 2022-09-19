package pl.coderslab.diywithspring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login() {
        return "static/login";
    }


    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        userService.saveUser(user);
        return "user";
    }

    @GetMapping("/create-admin")
    @ResponseBody
    public String createAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveAdmin(user);
        return "admin";
    }

    @GetMapping("/admin/admin")
    @ResponseBody
    public String checkAccessAdmin() {
        return "access for admin";
    }

    @GetMapping("/user/user")
    @ResponseBody
    public String checkAccessUser() {
        return "access for user";
    }
}