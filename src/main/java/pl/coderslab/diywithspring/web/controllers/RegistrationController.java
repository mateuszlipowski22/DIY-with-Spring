package pl.coderslab.diywithspring.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.dto.UserDTO;
import pl.coderslab.diywithspring.dto.UserDetailsDTO;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.models.UserDetails;
import pl.coderslab.diywithspring.services.interfaces.UserDetailsService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public RegistrationController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "static/register";
    }

    @Transactional
    @PostMapping("/register")
    public String processRegistration(@Valid UserDTO userDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "static/register";
        }
        User userToSave= new User();
        userToSave.setPassword(userDTO.getPassword());
        userToSave.setUsername(userDTO.getUsername());
        userToSave.setEmail(userDTO.getEmail());
        userToSave.setConfirmation(userDTO.isConfirmation());
        UserDetails userToSaveDetails = new UserDetails();
        userToSave.setUserDetails(userToSaveDetails);
        userToSaveDetails.setUser(userToSave);
        userService.saveUser(userToSave);

        model.addAttribute("userDetailsDTO", new UserDetailsDTO());
        User userFromDB = userService.findByUserName(userToSave.getUsername());
        model.addAttribute("newUserId", userFromDB.getId());
        return "static/registerUserDetails";
    }

    @PostMapping("/register/userDetails")
    public String processRegistrationDetailsForm(@Valid UserDetailsDTO userDetailsDTO, BindingResult bindingResult, @RequestParam("newUserId") Long newUserId, MultipartFile imageFile) {
        if (bindingResult.hasErrors()) {
            return "static/registerUserDetails";
        }
        User userDB=userService.findUserById(newUserId);
        UserDetails userDetailsDB = userDB.getUserDetails();
        userDetailsDB.setAboutMe(userDetailsDTO.getAboutMe());
        userDetailsDB.setCity(userDetailsDTO.getCity());
        userDetailsDB.setAvatar(userDetailsService.getByteAvatar(imageFile));
        userDetailsService.saveUserDetails(userDetailsDB);
        return "redirect:/login";
    }

}
