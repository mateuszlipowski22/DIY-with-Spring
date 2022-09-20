package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.diywithspring.models.Tool;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool,Long> {

    List<Tool> findAllByUserId(Long userId);

}
