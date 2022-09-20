package pl.coderslab.diywithspring.services;

import pl.coderslab.diywithspring.models.Tool;

import java.util.List;

public interface ToolService {

    void saveTool(Tool tool);
    List<Tool> getUserToolList(Long userId);

}
