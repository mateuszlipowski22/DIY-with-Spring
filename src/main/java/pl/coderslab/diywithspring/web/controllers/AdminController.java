package pl.coderslab.diywithspring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.RoleService;
import pl.coderslab.diywithspring.services.UserService;


import javax.validation.Valid;

@Controller
@RequestMapping("admin/user/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("list")
    public String showUsersList(Model model){
        model.addAttribute("users", userService.findAllUsersByRoles(roleService.findByName("ROLE_USER")));
        return "admin/user/list";
    }

    @GetMapping("{userId}/delete")
    public String showDeleteUserForm(Model model, @PathVariable Long userId) {
        model.addAttribute("user", userService.findUserById(userId));
        return "admin/user/delete";
    }

    @PostMapping("/delete")
    public String processDeleteUser(Long id) {
        userService.deleteUserRoleByUserId(id);
        userService.deleteUserById(id);
        return "redirect:/admin/user/list";
    }

    @GetMapping("{userId}/edit")
    public String showEditUserForm(@PathVariable Long userId, Model model){
        model.addAttribute("user", userService.findUserById(userId));
        return "admin/user/edit";
    }

    @PostMapping("{userId}/edit")
    public String processEditUser(@Valid User user, BindingResult bindingResult, @PathVariable Long userId){
        if(bindingResult.hasErrors()){
            return "admin/user/edit";
        }
        User userDB=userService.findUserById(userId);
        userDB.setEmail(user.getEmail());
        userDB.setUsername(user.getUsername());
        userService.saveUser(userDB);
        return "redirect:/admin/user/list";
    }


    @GetMapping("{userId}/show")
    public String showUser(Model model, @PathVariable Long userId){
        model.addAttribute("user", userService.findUserById(userId));
        return "admin/user/show";
    }
}

