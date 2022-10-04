package pl.coderslab.diywithspring.web.controllers.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.diywithspring.models.CurrentUser;
import pl.coderslab.diywithspring.models.chat.MessageDB;
import pl.coderslab.diywithspring.models.chat.MessageModel;
import pl.coderslab.diywithspring.services.interfaces.chat.MessageDBService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin

@Slf4j
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageDBService messageDBService;

    public MessageController(SimpMessagingTemplate simpMessagingTemplate, MessageDBService messageDBService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageDBService = messageDBService;
    }


    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        MessageDB messageDBToSave = new MessageDB();
        messageDBToSave.setMessage(message.getMessage());
        messageDBToSave.setCreatedOn(LocalDateTime.parse(message.getCreatedOn(), DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")));
        messageDBToSave.setFromLogin(message.getFromLogin());
        messageDBToSave.setToLogin(message.getToLogin());
        messageDBToSave.setChatName(null);
        messageDBService.saveMessageToDB(messageDBToSave);
        System.out.println("handling send message: " + message + " to: " + to);
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    @MessageMapping("/chat/chatroom/{chatroomName}")
    public void sendMessageToChatroom(@DestinationVariable String chatroomName, MessageModel message) {
        MessageDB messageDBToSave = new MessageDB();
        messageDBToSave.setMessage(message.getMessage());
        messageDBToSave.setCreatedOn(LocalDateTime.parse(message.getCreatedOn(), DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")));
        messageDBToSave.setFromLogin(message.getFromLogin());
        messageDBToSave.setToLogin(null);
        messageDBToSave.setChatName(message.getChatName());
        messageDBService.saveMessageToDB(messageDBToSave);
        System.out.println("handling send message: " + message + " to: " + chatroomName);
        simpMessagingTemplate.convertAndSend("/chatroom/messages/" + chatroomName, message);
    }

    @RequestMapping(value = {"/user/chat/{toLogin}"}, method = RequestMethod.GET)
    public List<MessageModel> getMessageByUsers(@PathVariable String toLogin, @AuthenticationPrincipal CurrentUser currentUser){

        List<MessageDB> messageDBList = messageDBService.findAllByLoginToAndLoginFrom(toLogin, currentUser.getUser().getUsername());
        return messageDBList.stream().map(messageDBService::convertFromMessageDBtoMessageModel).collect(Collectors.toList());
    }


    @RequestMapping(value = {"/user/chatroom/{chatRoomName}"}, method = RequestMethod.GET)
    public List<MessageModel> getMessageByChatRoomName(@PathVariable String chatRoomName){

        List<MessageDB> messageDBList = messageDBService.findAllByChatRoomName(chatRoomName);
        return messageDBList.stream().map(messageDBService::convertFromMessageDBtoMessageModel).collect(Collectors.toList());
    }
}
