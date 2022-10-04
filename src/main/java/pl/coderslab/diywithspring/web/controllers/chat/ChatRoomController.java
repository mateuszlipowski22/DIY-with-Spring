package pl.coderslab.diywithspring.web.controllers.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.diywithspring.models.chat.ChatRoom;
import pl.coderslab.diywithspring.services.interfaces.chat.ChatRoomService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
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
}
