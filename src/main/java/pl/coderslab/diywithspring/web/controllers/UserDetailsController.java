package pl.coderslab.diywithspring.web.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.dto.UserDetailsDTO;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.models.UserDetails;
import pl.coderslab.diywithspring.services.interfaces.UserDetailsService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("user/details/")
public class UserDetailsController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("show")
    public String showUser(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("currentUser", currentUser.getUser());
        model.addAttribute("userDetails", userDetailsService.getUserDetails(currentUser.getUser().getId()));
        return "user/details/show";
    }



    @GetMapping("image")
    public String showUploadFile(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("userDetails", userDetailsService.getUserDetails(currentUser.getUser().getId()));
        return "user/details/imageUploadForm";
    }

//    @PostMapping("image")
//    public String handleImagePost(@RequestParam("imageFile") MultipartFile imageFile, @AuthenticationPrincipal CurrentUser currentUser){
//        userDetailsService.saveImageFile(currentUser.getUser().getId(), imageFile);
//        return "redirect:/user/details/show";
//    }


    @GetMapping("showAvatar")
    private void renderImageFromDB(@AuthenticationPrincipal CurrentUser currentUser, HttpServletResponse response) throws IOException {
        UserDetails userDetailsDB = userDetailsService.getUserDetails(currentUser.getUser().getId());
        byte[] byteArray = new byte[userDetailsService.getUserDetails(currentUser.getUser().getId()).getAvatar().length];

        int i=0;
        for (Byte wrappedByte : userDetailsDB.getAvatar()){
            byteArray[i++]=wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("{userId}/showAvatar")
    private void renderImageFromDB(@PathVariable Long userId, HttpServletResponse response) throws IOException {
        UserDetails userDetailsDB = userDetailsService.getUserDetails(userId);
        byte[] byteArray = new byte[userDetailsService.getUserDetails(userId).getAvatar().length];

        int i=0;
        for (Byte wrappedByte : userDetailsDB.getAvatar()){
            byteArray[i++]=wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }


    @GetMapping("edit")
    public String showEditUserDetailsForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("userDetailsDTO", userDetailsService.getUserDetails(currentUser.getUser().getId()));
        return "user/details/editUserDetails";
    }

    @PostMapping("edit")
    public String processEditUserDetailsForm(@Valid UserDetailsDTO userDetailsDTO, BindingResult bindingResult, @AuthenticationPrincipal CurrentUser currentUser, MultipartFile imageFile) {
        if (bindingResult.hasErrors()) {
            return "user/details/editUserDetails";
        }
        User userDB=userService.findUserById(currentUser.getUser().getId());
        UserDetails userDetailsDB = userDB.getUserDetails();
        userDetailsDB.setAboutMe(userDetailsDTO.getAboutMe());
        userDetailsDB.setCity(userDetailsDTO.getCity());
        userDetailsDB.setAvatar(userDetailsService.getByteAvatar(imageFile));
        userDetailsService.saveUserDetails(userDetailsDB);
        return "redirect:/user/details/show";
    }
}
