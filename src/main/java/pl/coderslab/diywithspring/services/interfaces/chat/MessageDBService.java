package pl.coderslab.diywithspring.services.interfaces.chat;

import pl.coderslab.diywithspring.models.chat.MessageDB;
import pl.coderslab.diywithspring.models.chat.MessageModel;

import java.util.List;

public interface MessageDBService {

    List<MessageDB> findAllByLoginToAndLoginFrom(String loginTo, String loginFrom);
    void saveMessageToDB(MessageDB messageDB);
    MessageModel convertFromMessageDBtoMessageModel(MessageDB messageDB);
    List<MessageDB> findAllByChatRoomName(String chatRoomName);
    void deleteMessageByChatRoomName(String chatRoomName);
}
