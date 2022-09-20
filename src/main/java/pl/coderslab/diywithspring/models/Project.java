package pl.coderslab.diywithspring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany
    private Set<Comment> comments;

    @OneToMany
    private Set<Step> steps;

    @OneToMany
    private Set<Tool> tools;
}