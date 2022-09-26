package pl.coderslab.diywithspring.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.diywithspring.validators.ValidPassword;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @Size(min=2, max=60, message = "Username has to be between 2 and 60 characters,")
    private String username;

    @ValidPassword(message = "Password has to be between 2 and 30 characters," +
            " at least one uppercase one lowercase and one digit." +
            " Whitespaces are not allowed")
    private String password;

    @Email(message = "Please input correct email address")
    private String email;

    private int enabled;

    @AssertTrue(message = "You have to agree with the terms and conditions ")
    private boolean confirmation;

}
