package pl.coderslab.diywithspring.services.implemetations.chat;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.chat.ChatRoom;
import pl.coderslab.diywithspring.repositories.chat.ChatRoomRepository;
import pl.coderslab.diywithspring.services.interfaces.chat.ChatRoomService;
import pl.coderslab.diywithspring.services.interfaces.chat.MessageDBService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }


    @Override
    public List<String> findAllChatRoomNames() {
        return chatRoomRepository.findAll().stream().map(chatRoom -> chatRoom.getChatRoomName()).collect(Collectors.toList());
    }

    @Override
    public void saveChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    @Override
    @Transactional
    public void deleteChatRoomByName(String chatRoomName){
        chatRoomRepository.deleteByChatRoomName(chatRoomName);
    }
}
