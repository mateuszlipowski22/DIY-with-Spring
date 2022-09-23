package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.diywithspring.models.Role;
import pl.coderslab.diywithspring.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByRolesContaining(Role role);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_role WHERE user_id = ?1", nativeQuery = true)
    void deleteUserRole(Long id);
}