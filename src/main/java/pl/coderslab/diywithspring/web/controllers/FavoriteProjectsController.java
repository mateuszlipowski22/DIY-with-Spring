package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.FavoriteProject;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.models.User;
import pl.coderslab.diywithspring.services.interfaces.FavoriteProjectService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

@Controller
@RequestMapping("user/")
public class FavoriteProjectsController {

    final private FavoriteProjectService favoriteProjectService;
    final private ProjectService projectService;

    public FavoriteProjectsController(FavoriteProjectService favoriteProjectService, ProjectService projectService) {
        this.favoriteProjectService = favoriteProjectService;
        this.projectService = projectService;
    }


    @GetMapping("favoriteProjects/all")
    public String showUserFavoriteProjects(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("favoriteProjects", favoriteProjectService.findFavoriteProjectsByUserID(currentUser.getUser().getId()));
        return "user/favoriteProjects/all";
    }

    @GetMapping("favoriteProjects/{projectID}/addToFavorite")
    public String addProjectToFavorite(@PathVariable Long projectID, @AuthenticationPrincipal CurrentUser currentUser){
        if(favoriteProjectService.findFavoriteProjectsByUserID(currentUser.getUser().getId()).stream().noneMatch(favoriteProject -> favoriteProject.getFavoriteProject().getId().equals(projectID))){
            Project projectToAddToFavorite = projectService.findProjectByID(projectID);
            FavoriteProject favoriteProjectToAdd = FavoriteProject.builder().favoriteProject(projectToAddToFavorite).user(currentUser.getUser()).build();
            favoriteProjectService.saveFavoriteProject(favoriteProjectToAdd);
        }

        return "redirect:/user/favoriteProjects/all";
    }

    @GetMapping("favoriteProjects/{favoriteProjectID}/removeFromFavorite")
    public String removeProjectFromFavorite(@PathVariable Long favoriteProjectID, @AuthenticationPrincipal CurrentUser currentUser){
        if(favoriteProjectService.findFavoriteProjectsByUserID(currentUser.getUser().getId()).stream().anyMatch(favoriteProject -> favoriteProject.getFavoriteProject().getId().equals(favoriteProjectService.findFavoriteProjectByID(favoriteProjectID).getFavoriteProject().getId()))){
            favoriteProjectService.deleteFavoriteProject(favoriteProjectService.findFavoriteProjectByID(favoriteProjectID));
        }
        return "redirect:/user/favoriteProjects/all";
    }

}
