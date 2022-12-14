package pl.coderslab.diywithspring.services.interfaces;

import pl.coderslab.diywithspring.dto.CommentDTO;
import pl.coderslab.diywithspring.models.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);
    List<Comment> findCommentsByProjectId(Long projectId);

    Comment findCommentByID(Long commentId);

    void deleteCommentById(Long commentId);

    CommentDTO convertCommandIntoCommandDTO(Comment comment);

    void deleteCommentByUserId(Long userId);
}
