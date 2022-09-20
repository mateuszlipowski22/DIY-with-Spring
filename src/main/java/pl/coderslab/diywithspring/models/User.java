package pl.coderslab.diywithspring.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    private String password;
    private String email;
    private int enabled;
    private boolean confirmation;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
    private UserDetails userDetails;

    @OneToMany(mappedBy = "user")
    private Set<Project> projects;

    @OneToMany(mappedBy = "user")
    private Set<Tool> tools;
}
