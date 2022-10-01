package pl.coderslab.diywithspring.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String title;

    private String description;

    private String components;

    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany
    private List<Step> steps;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tool> tools;

    @Lob
    private Byte[] image;

}
