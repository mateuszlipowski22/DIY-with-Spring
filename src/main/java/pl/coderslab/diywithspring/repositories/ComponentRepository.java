package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.Component;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Long> {

    List<Component> findAllByProjectId(Long projectId);

}
