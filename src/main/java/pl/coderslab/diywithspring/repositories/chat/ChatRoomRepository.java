package pl.coderslab.diywithspring.repositories.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.chat.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
}
