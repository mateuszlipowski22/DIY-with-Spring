package pl.coderslab.diywithspring.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.services.interfaces.CommentService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;
import pl.coderslab.diywithspring.services.interfaces.UserService;

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
    public String showAddComment(@RequestParam("projectId") Long projectId, Model model) {
        model.addAttribute("project", projectService.findProjectByID(projectId));
        model.addAttribute("comment", new Comment());
        return "/user/project/comment/addComment";
    }


    @PostMapping("add")
    public String processAddComment(Comment comment, @RequestParam("projectId") Long projectId, @AuthenticationPrincipal CurrentUser currentUser) {
        Comment commentToSave = new Comment();
        commentToSave.setContent(comment.getContent());
        commentToSave.setProject(projectService.findProjectByID(projectId));
        commentToSave.setUser(currentUser.getUser());
        Project projectToUpdate = projectService.findProjectByID(projectId);
        projectToUpdate.getComments().add(commentToSave);
        projectService.saveProject(projectToUpdate);
        return "redirect:/user/project/" + projectId + "/show";
//        websocket spring chat between users, dto, security
    }

    @GetMapping("edit")
    public String showEditComment(@RequestParam("projectId") Long projectId, @RequestParam("commentId") Long commentId, Model model) {
        model.addAttribute("project", projectService.findProjectByID(projectId));
        model.addAttribute("comment", commentService.findCommentByID(commentId));
        return "/user/project/comment/editComment";
    }

    @PostMapping("edit")
    public String processEditComment(Comment comment, @RequestParam("projectId") Long projectId, @RequestParam("commentId") Long commentId) {
        Comment commentToUpdate = commentService.findCommentByID(commentId);
        commentToUpdate.setContent(comment.getContent());
        commentService.saveComment(commentToUpdate);
        return "redirect:/user/project/" + projectId + "/show";
    }

    @GetMapping("delete")
    public String showDeleteComment(@RequestParam("projectId") Long projectId, @RequestParam("commentId") Long commentId, Model model) {
        model.addAttribute("comment", commentService.findCommentByID(commentId));
        model.addAttribute("project", projectService.findProjectByID(projectId));
        return "/user/project/comment/deleteComment";
    }

    @PostMapping("delete")
    public String processDeleteComment(@RequestParam("projectId") Long projectId, @RequestParam("commentId") Long commentId) {
        Project projectWithCommentToDelete = projectService.findProjectByID(projectId);
        projectWithCommentToDelete.getComments().remove(commentService.findCommentByID(commentId));
        commentService.deleteCommentById(commentId);
        return "redirect:/user/project/" + projectId + "/show";
    }
}
