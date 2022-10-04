package pl.coderslab.diywithspring.models.chat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {

    private String message;
    private String fromLogin;
    private String toLogin;
    private String createdOn;
    private String chatName;

}
