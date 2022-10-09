package pl.coderslab.diywithspring.services.interfaces;


import pl.coderslab.diywithspring.dto.CommentDTO;
import pl.coderslab.diywithspring.dto.ComponentDTO;
import pl.coderslab.diywithspring.models.Comment;
import pl.coderslab.diywithspring.models.Component;

import java.util.List;

public interface ComponentService {

    void saveComponent(Component component);
    List<Component> findComponentsByProjectId(Long projectId);

    Component findComponentByID(Long componentId);

    void deleteComponentById(Long componentId);

    ComponentDTO convertComponentIntoComponentDTO(Component component);

}
