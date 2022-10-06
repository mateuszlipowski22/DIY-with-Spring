package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.diywithspring.models.FavoriteProject;

import java.util.List;

public interface FavoriteProjectRepository extends JpaRepository<FavoriteProject, Long> {

    List<FavoriteProject> findAllByUserId(Long userID);
}
