package pl.coderslab.diywithspring.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {

    private String message;
    private String fromLogin;

}
