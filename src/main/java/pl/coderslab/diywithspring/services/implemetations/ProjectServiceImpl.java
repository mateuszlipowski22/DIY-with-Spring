package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.repositories.ProjectRepository;
import pl.coderslab.diywithspring.services.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findProjectByID(Long id) {
        return projectRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Project> findProjectsByUserID(Long id) {
        return projectRepository.findAllByUserId(id);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }


}
