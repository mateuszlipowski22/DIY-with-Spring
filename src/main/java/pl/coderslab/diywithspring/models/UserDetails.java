package pl.coderslab.diywithspring.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Slf4j
@ToString
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String city;

    private String aboutMe;

    private LocalDateTime createdOn;

    @Lob
    private Byte[] avatar;

    @PrePersist
    public void prePersist() throws IOException {
        createdOn = LocalDateTime.now();
    }
}
