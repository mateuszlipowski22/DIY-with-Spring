package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.models.UserDetails;
import pl.coderslab.diywithspring.services.ToolService;
import pl.coderslab.diywithspring.services.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public RegistrationController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "static/register";
    }

    @Transactional
    @PostMapping("/register")
    public String processRegistration(@Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "static/register";
        }
        User userToSave= new User();
        userToSave.setPassword(user.getPassword());
        userToSave.setUsername(user.getUsername());
        userToSave.setEmail(user.getEmail());
        userToSave.setConfirmation(user.isConfirmation());
        UserDetails userToSaveDetails = new UserDetails();
        userToSave.setUserDetails(userToSaveDetails);
        userService.saveUser(userToSave);
        return "redirect:/login";
    }
}
