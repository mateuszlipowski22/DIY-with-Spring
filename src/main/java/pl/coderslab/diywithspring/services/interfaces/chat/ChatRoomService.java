package pl.coderslab.diywithspring.services.interfaces.chat;

import pl.coderslab.diywithspring.models.chat.ChatRoom;

import java.util.List;

public interface ChatRoomService {

    List<String> findAllChatRoomNames();
    void saveChatRoom(ChatRoom chatRoom);
}
