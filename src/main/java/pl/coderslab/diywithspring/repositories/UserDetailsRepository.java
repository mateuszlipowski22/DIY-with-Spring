package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.diywithspring.models.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

    UserDetails findByUserId(Long id);

}