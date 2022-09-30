package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.chat.MessageDB;
import pl.coderslab.diywithspring.models.chat.MessageModel;
import pl.coderslab.diywithspring.repositories.MessageDBRepository;
import pl.coderslab.diywithspring.services.interfaces.MessageDBService;

import java.util.List;

@Service
public class MessageDBServiceImpl implements MessageDBService {

    private final MessageDBRepository messageDBRepository;

    public MessageDBServiceImpl(MessageDBRepository messageDBRepository) {
        this.messageDBRepository = messageDBRepository;
    }

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
        messageModel.setCreatedOn(messageDB.getCreatedOn().toString());
        return messageModel;
    }
}
