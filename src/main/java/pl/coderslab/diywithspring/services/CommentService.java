package pl.coderslab.diywithspring.services;

import pl.coderslab.diywithspring.models.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);
    List<Comment> findCommentsByProjectId(Long projectId);

}
