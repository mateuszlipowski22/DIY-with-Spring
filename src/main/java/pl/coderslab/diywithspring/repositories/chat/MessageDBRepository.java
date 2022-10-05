package pl.coderslab.diywithspring.repositories.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.diywithspring.models.chat.MessageDB;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageDBRepository extends JpaRepository<MessageDB, Long> {


    @Query(value = "SELECT * FROM messagedb WHERE (from_login = ?1 and to_login = ?2) or (from_login = ?2 and to_login = ?1) ORDER BY created_on", nativeQuery = true)
    List<MessageDB> findAllMessagesBetweenUsers(String fromLogin, String toLogin);

    List<MessageDB> findAllByChatNameOrderByCreatedOn(String chatRoomName);

    void deleteByChatName(String chatName);
}
