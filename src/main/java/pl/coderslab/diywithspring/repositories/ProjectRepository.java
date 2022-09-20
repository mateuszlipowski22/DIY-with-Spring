package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.diywithspring.models.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findAllByUserId(Long userId);

}
