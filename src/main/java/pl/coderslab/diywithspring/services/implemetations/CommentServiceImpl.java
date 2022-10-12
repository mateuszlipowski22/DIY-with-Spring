package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.dto.CommentDTO;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.repositories.CommentRepository;
import pl.coderslab.diywithspring.services.interfaces.CommentService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;

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

    @Override
    public Comment findCommentByID(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDTO convertCommandIntoCommandDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        commentDTO.setId(comment.getId());
        commentDTO.setCreatedOn(comment.getCreatedOn());
        commentDTO.setProject(comment.getProject());
        commentDTO.setUser(comment.getUser());
        commentDTO.setUpdatedOn(comment.getUpdatedOn());
        return commentDTO;
    }

    @Override
    public void deleteCommentByUserId(Long userId) {
        commentRepository.deleteAllByUserId(userId);
    }
}
