package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.diywithspring.dto.CommentDTO;
import pl.coderslab.diywithspring.dto.ComponentDTO;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.Component;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.services.interfaces.ComponentService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user/project/component/")
public class ComponentController {

    private final ComponentService componentService;
    private final ProjectService projectService;

    public ComponentController(ComponentService componentService, ProjectService projectService) {
        this.componentService = componentService;
        this.projectService = projectService;
    }

    @GetMapping("add")
    public String showAddComponent(@RequestParam("projectId") Long projectId, Model model) {
        model.addAttribute("project", projectService.findProjectByID(projectId));
        model.addAttribute("componentDTO", new ComponentDTO());
        return "/user/project/component/addComponent";
    }

    @PostMapping("add")
    public String processAddComponent(@Valid ComponentDTO componentDTO, BindingResult bindingResult, @RequestParam("projectId") Long projectId, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("project", projectService.findProjectByID(projectId));
            return "/user/project/component/addComponent";
        }
        Component componentToSave = new Component();
        componentToSave.setDescription(componentDTO.getDescription());
        componentToSave.setQuantity(componentDTO.getQuantity());
        componentToSave.setProject(projectService.findProjectByID(projectId));
        Project projectToUpdate = projectService.findProjectByID(projectId);
        projectToUpdate.getComponents().add(componentToSave);
        model.addAttribute("project", projectService.saveProjectAndReturn(projectToUpdate));
        model.addAttribute("componentDTO", new ComponentDTO());
        return "/user/project/component/addComponent";
    }

    @PostMapping("edit")
    public String processEditComponent(@Valid ComponentDTO componentDTO, BindingResult bindingResult, @RequestParam("projectId") Long projectId, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("project", projectService.findProjectByID(projectId));
            return "/user/project/component/editComponent";
        }
        Component componentToUpdate = new Component();
        componentToUpdate.setDescription(componentDTO.getDescription());
        componentToUpdate.setQuantity(componentDTO.getQuantity());
        componentToUpdate.setProject(projectService.findProjectByID(projectId));
        Project projectToUpdate = projectService.findProjectByID(projectId);
        projectToUpdate.getComponents().add(componentToUpdate);
        model.addAttribute("project", projectService.saveProjectAndReturn(projectToUpdate));
        model.addAttribute("componentDTO", new ComponentDTO());
        return "/user/project/component/editComponent";
    }

    @PostMapping("delete")
    public String processDeleteComponent(@RequestParam("projectId") Long projectId, @RequestParam("componentId") Long componentId, Model model) {
        Project projectToUpdate=projectService.findProjectByID(projectId);
        projectToUpdate.getComponents().remove(componentService.findComponentByID(componentId));
        projectService.saveProject(projectToUpdate);
        componentService.deleteComponentById(componentId);
        model.addAttribute("project", projectService.findProjectByID(projectId));
        model.addAttribute("componentDTO", new ComponentDTO());
        return "/user/project/component/editComponent";
    }
}
