package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.repositories.CommentRepository;
import pl.coderslab.diywithspring.services.CommentService;
import pl.coderslab.diywithspring.services.ProjectService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProjectService projectService;

    public CommentServiceImpl(CommentRepository commentRepository, ProjectService projectService) {
        this.commentRepository = commentRepository;
        this.projectService = projectService;
    }



    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByProjectId(Long projectId) {
        return commentRepository.findAllByProjectId(projectId);
    }
}
