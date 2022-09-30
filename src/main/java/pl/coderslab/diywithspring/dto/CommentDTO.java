package pl.coderslab.diywithspring.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.models.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {

    private Long id;

    private String content;

    private User user;

    private Project project;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
