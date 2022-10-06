package pl.coderslab.diywithspring.services.implemetations.chat;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.chat.MessageDB;
import pl.coderslab.diywithspring.models.chat.MessageModel;
import pl.coderslab.diywithspring.repositories.chat.MessageDBRepository;
import pl.coderslab.diywithspring.services.interfaces.chat.MessageDBService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageDBServiceImpl implements MessageDBService {

    private final MessageDBRepository messageDBRepository;

    public MessageDBServiceImpl(MessageDBRepository messageDBRepository) {
        this.messageDBRepository = messageDBRepository;
    }

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<MessageDB> findAllByLoginToAndLoginFrom(String loginTo, String loginFrom) {
        return messageDBRepository.findAllMessagesBetweenUsers(loginTo, loginFrom);
    }

    @Override
    public void saveMessageToDB(MessageDB messageDB) {
        messageDBRepository.save(messageDB);
    }

    @Override
    public MessageModel convertFromMessageDBtoMessageModel(MessageDB messageDB) {
        MessageModel messageModel = new MessageModel();
        messageModel.setMessage(messageDB.getMessage());
        messageModel.setFromLogin(messageDB.getFromLogin());
        messageModel.setToLogin(messageDB.getToLogin());
        messageModel.setChatName(messageDB.getChatName());
        messageModel.setCreatedOn(messageDB.getCreatedOn().format(formatter));
        return messageModel;
    }

    @Override
    public List<MessageDB> findAllByChatRoomName(String chatRoomName) {
        return messageDBRepository.findAllByChatNameOrderByCreatedOn(chatRoomName);
    }

    @Override
    public void deleteMessageByChatRoomName(String chatRoomName) {
        messageDBRepository.deleteAllByChatName(chatRoomName);
    }
}
