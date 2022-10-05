package pl.coderslab.diywithspring.web.controllers.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.diywithspring.models.chat.ChatRoom;
import pl.coderslab.diywithspring.services.interfaces.chat.ChatRoomService;
import pl.coderslab.diywithspring.services.interfaces.chat.MessageDBService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final MessageDBService messageDBService;

    public ChatRoomController(ChatRoomService chatRoomService, MessageDBService messageDBService) {
        this.chatRoomService = chatRoomService;
        this.messageDBService = messageDBService;
    }

    @GetMapping("/fetchAllChatRoom")
    public List<String> fetchAllChatRoom() {
        return chatRoomService.findAllChatRoomNames();
    }

    @GetMapping("/registration/{chatRoomName}")
    public ResponseEntity<Void> register(@PathVariable String chatRoomName) {
        System.out.println("handling register user request: " + chatRoomName);
        try {
            ChatRoom chatRoomToSave = new ChatRoom();
            chatRoomToSave.setChatRoomName(chatRoomName);
            chatRoomService.saveChatRoom(chatRoomToSave);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/chatroom/delete/{chatRoomName}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable String chatRoomName) {
        try {
            messageDBService.deleteMessageByChatRoomName(chatRoomName);
            chatRoomService.deleteChatRoomByName(chatRoomName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
