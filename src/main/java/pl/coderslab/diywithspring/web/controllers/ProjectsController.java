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
public class ProjectsController {

    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    private final ProjectService projectService;

    @GetMapping("projects/all")
    public String showUserProjects(Model model){
        model.addAttribute("projects", projectService.findAll());
        return "user/project/all";
    }

    @GetMapping("/project/{projectId}/show")
    public String showProject(Model model, @PathVariable Long projectId){
        model.addAttribute("project", projectService.findProjectByID(projectId));
        return "user/project/show";
    }

}
