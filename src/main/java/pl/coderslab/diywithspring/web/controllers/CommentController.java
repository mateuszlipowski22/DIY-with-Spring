package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.services.CommentService;
import pl.coderslab.diywithspring.services.ProjectService;
import pl.coderslab.diywithspring.services.UserService;

@Controller
@RequestMapping("/user/project/comment/")
public class CommentController {

    private final CommentService commentService;
    private final ProjectService projectService;
    private final UserService userService;

    public CommentController(CommentService commentService, ProjectService projectService, UserService userService) {
        this.commentService = commentService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("add")
    public String showAddComment(@RequestParam("projectId") Long projectId, Model model){
        model.addAttribute("project", projectService.findProjectByID(projectId));
        model.addAttribute("comment", new Comment());
        return "/user/project/comment/addComment";
    }


    @PostMapping ("add")
    public String processAddComment(Comment comment, @RequestParam("projectId") Long projectId, @AuthenticationPrincipal CurrentUser currentUser) {
        Comment commentToSave=new Comment();
        commentToSave.setContent(comment.getContent());
        commentToSave.setProject(projectService.findProjectByID(projectId));
        commentToSave.setUser(currentUser.getUser());
//        commentService.saveComment(commentToSave);
        Project projectToUpdate = projectService.findProjectByID(projectId);
        projectToUpdate.getComments().add(commentToSave);
        projectService.saveProject(projectToUpdate);
        return "redirect:/user/project/"+projectId+"/show";
    }

}
