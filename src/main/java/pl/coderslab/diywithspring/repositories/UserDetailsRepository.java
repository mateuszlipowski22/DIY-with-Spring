package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.diywithspring.models.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

    UserDetails findByUserId(Long id);

}