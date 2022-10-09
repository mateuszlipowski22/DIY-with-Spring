package pl.coderslab.diywithspring.services.implemetations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.dto.CommentDTO;
import pl.coderslab.diywithspring.dto.ComponentDTO;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.Component;
import pl.coderslab.diywithspring.repositories.ComponentRepository;
import pl.coderslab.diywithspring.services.interfaces.ComponentService;
import pl.coderslab.diywithspring.services.interfaces.ProjectService;

import java.util.List;

@Service
@Slf4j
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;
    private final ProjectService projectService;

    public ComponentServiceImpl(ComponentRepository componentRepository, ProjectService projectService) {
        this.componentRepository = componentRepository;
        this.projectService = projectService;
    }

    @Override
    public void saveComponent(Component component) {
        componentRepository.save(component);
    }

    @Override
    public List<Component> findComponentsByProjectId(Long projectId) {
        return componentRepository.findAllByProjectId(projectId);
    }

    @Override
    public Component findComponentByID(Long componentId) {
        return componentRepository.findById(componentId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteComponentById(Long commentId) {
        componentRepository.deleteById(commentId);
    }

    @Override
    public ComponentDTO convertComponentIntoComponentDTO(Component component) {
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setDescription(component.getDescription());
        componentDTO.setId(component.getId());
        componentDTO.setQuantity(componentDTO.getQuantity());
        componentDTO.setProject(component.getProject());
        return componentDTO;
    }
}
