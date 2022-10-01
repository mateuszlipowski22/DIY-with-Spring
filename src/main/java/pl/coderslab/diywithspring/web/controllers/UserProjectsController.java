package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("user/")
public class UserProjectsController {

    private final ProjectService projectService;
    private final UserService userService;

    public UserProjectsController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }


    @GetMapping("projects")
    public String showUserProjects(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("userProjects", projectService.findProjectsByUserID(currentUser.getUser().getId()));
        return "user/project/userProjectList";
    }

    @GetMapping("/project/edit")
    public String showEditProjectForm(Model model, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("projectID") Long projectID){
        User actualUser=userService.findUserById(currentUser.getUser().getId());
        Project projectToEdit =projectService.findProjectByID(projectID);
        projectToEdit.setTools(actualUser.getTools());
        model.addAttribute("project", projectToEdit);
        return "user/project/editProject";
    }

    @PostMapping("/project/edit")
    public String processEditProjectForm(@Valid Project project, BindingResult bindingResult, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("projectID") Long projectID){
        if(bindingResult.hasErrors()){
            return "user/project/editProject";
        }
        Project projectToUpdate = projectService.findProjectByID(projectID);
        projectToUpdate.setCategory(project.getCategory());
        projectToUpdate.setComponents(project.getComponents());
        projectToUpdate.setTitle(project.getTitle());
        projectToUpdate.setDescription(project.getDescription());
        projectToUpdate.setImage(projectService.getByteImage(imageFile));
        projectToUpdate.setUser(currentUser.getUser());
        projectToUpdate.getTools().clear();
        projectToUpdate.setTools(project.getTools());
        projectService.saveProject(projectToUpdate);
        return "redirect:/user/projects";
    }

}

