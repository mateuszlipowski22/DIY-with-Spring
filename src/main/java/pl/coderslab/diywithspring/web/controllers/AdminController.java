package pl.coderslab.diywithspring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.CommentService;
import pl.coderslab.diywithspring.services.interfaces.RoleService;
import pl.coderslab.diywithspring.services.interfaces.UserService;


import javax.validation.Valid;

@Controller
@RequestMapping("admin/user/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final CommentService commentService;


    public AdminController(UserService userService, RoleService roleService, CommentService commentService) {
        this.userService = userService;
        this.roleService = roleService;
        this.commentService = commentService;
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

    @Transactional
    @PostMapping("/delete")
    public String processDeleteUser(@RequestParam("id") Long id) {
        userService.deleteUserRoleByUserId(id);
        commentService.deleteCommentByUserId(id);
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

