package pl.coderslab.diywithspring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String title;

    private String description;

    private String components;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany
    private List<Step> steps;

    @OneToMany
    private List<Tool> tools;

    @Lob
    private Byte[] image;
}
