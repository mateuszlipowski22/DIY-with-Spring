package pl.coderslab.diywithspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.diywithspring.models.Step;

public interface StepRepository extends JpaRepository<Step,Long> {
}
