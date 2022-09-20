package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.services.ProjectService;

@Controller
@RequestMapping("user/")
public class UserProjectsController {

    private final ProjectService projectService;


    public UserProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("projects")
    public String showUserProjects(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("userProjects", projectService.findProjectsByUserID(currentUser.getUser().getId()));
        return "user/project/userProjectList";
    }

}
