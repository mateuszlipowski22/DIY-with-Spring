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

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() throws IOException {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}