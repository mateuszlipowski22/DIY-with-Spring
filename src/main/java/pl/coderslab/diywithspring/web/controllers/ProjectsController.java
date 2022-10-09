package pl.coderslab.diywithspring.web.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.dto.ComponentDTO;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("user/")
public class ProjectsController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectsController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("projects/all")
    public String showUserProjects(Model model){
        model.addAttribute("projects", projectService.findAll());
        return "user/project/all";
    }

    @GetMapping("/project/{projectId}/show")
    public String showProject(Model model, @PathVariable Long projectId, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("project", projectService.findProjectByID(projectId));
        model.addAttribute("currentUserId", currentUser.getUser().getId());
        return "user/project/show";
    }

    @GetMapping("/project/add")
    public String showAddProjectForm(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        User actualUser=userService.findUserById(currentUser.getUser().getId());
        Project projectToView = new Project();
        projectToView.setTools(actualUser.getTools());
        model.addAttribute("project", projectToView);

        return "user/project/addProject";
    }

    @PostMapping("/project/add")
    public String processAddProjectForm(@Valid Project project,BindingResult bindingResult, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("imageFile") MultipartFile imageFile, Model model){
        if(bindingResult.hasErrors()){
            return "user/project/addProject";
        }
        Project projectToSave = new Project();
        projectToSave.setCategory(project.getCategory());
        projectToSave.setTitle(project.getTitle());
        projectToSave.setDescription(project.getDescription());
        projectToSave.setImage(projectService.getByteImage(imageFile));
        projectToSave.setUser(currentUser.getUser());
        projectToSave.setTools(project.getTools());
        projectService.saveProject(projectToSave);
//        return "redirect:/user/projects/all";
        model.addAttribute("project", projectToSave);
        model.addAttribute("componentDTO", new ComponentDTO());
        return "/user/project/component/addComponent";
    }

    @GetMapping("/project/{projectId}/showImage")
    private void renderImageFromDB(HttpServletResponse response, @PathVariable Long projectId) {
        byte[] byteArray = new byte[projectService.findProjectByID(projectId).getImage().length];

        int i=0;
        for (Byte wrappedByte : projectService.findProjectByID(projectId).getImage()){
            byteArray[i++]=wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        try {
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
