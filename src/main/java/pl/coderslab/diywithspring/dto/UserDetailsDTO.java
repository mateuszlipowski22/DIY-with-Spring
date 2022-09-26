package pl.coderslab.diywithspring.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDetailsDTO {

    private Long id;

    @NotBlank(message = "Please input your city name")
    private String city;

    @NotBlank(message = "Please write something about yourself")
    private String aboutMe;

    private LocalDateTime createdOn;

    @Lob
    private Byte[] avatar;
}
