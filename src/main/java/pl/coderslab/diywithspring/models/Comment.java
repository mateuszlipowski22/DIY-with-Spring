package pl.coderslab.diywithspring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    private LocalDateTime createdOn;


    @PrePersist
    public void prePersist() throws IOException {
        createdOn = LocalDateTime.now();
    }
}