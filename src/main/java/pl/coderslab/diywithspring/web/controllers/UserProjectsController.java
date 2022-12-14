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
import pl.coderslab.diywithspring.dto.ComponentDTO;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.ComponentService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user/")
public class UserProjectsController {

    private final ProjectService projectService;
    private final UserService userService;
    private final ComponentService componentService;

    public UserProjectsController(ProjectService projectService, UserService userService, ComponentService componentService) {
        this.projectService = projectService;
        this.userService = userService;
        this.componentService = componentService;
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
    public String processEditProjectForm(@Valid Project project,
                                         BindingResult bindingResult,
                                         @AuthenticationPrincipal CurrentUser currentUser,
                                         @RequestParam("imageFile") MultipartFile imageFile,
                                         @RequestParam("projectID") Long projectID,
                                         Model model){
        if(bindingResult.hasErrors()){
            return "user/project/editProject";
        }
        Project projectToUpdate = projectService.findProjectByID(projectID);
        projectToUpdate.setCategory(project.getCategory());
        projectToUpdate.setTitle(project.getTitle());
        projectToUpdate.setDescription(project.getDescription());
        projectToUpdate.setImage(projectService.getByteImage(imageFile));
        projectToUpdate.getTools().clear();
        projectToUpdate.setTools(project.getTools());
        projectService.saveProject(projectToUpdate);
        model.addAttribute("project", projectToUpdate);
        model.addAttribute("componentDTO", new ComponentDTO());
        return "/user/project/component/editComponent";
    }


    @GetMapping("/project/delete")
    public String showDeleteProjectForm(Model model, @RequestParam("projectID") Long projectID){
        Project projectToDelete =projectService.findProjectByID(projectID);
        model.addAttribute("project", projectToDelete);
        return "user/project/deleteProject";
    }

    @PostMapping("/project/delete")
    public String processDeleteProjectForm(@AuthenticationPrincipal CurrentUser currentUser,
                                           @RequestParam("projectID") Long projectID) {
        Project projectToDelete = projectService.findProjectByID(projectID);
        projectToDelete.setTools(null);
        projectService.saveProject(projectToDelete);
        projectService.deleteProjectByID(projectID);
        return "redirect:/user/projects";
    }

}

