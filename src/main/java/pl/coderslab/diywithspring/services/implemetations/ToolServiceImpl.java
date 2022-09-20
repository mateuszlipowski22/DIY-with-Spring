package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.Tool;
import pl.coderslab.diywithspring.repositories.ToolRepository;
import pl.coderslab.diywithspring.services.ToolService;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {

    public ToolServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    private final ToolRepository toolRepository;

    @Override
    public void saveTool(Tool tool) {
        toolRepository.save(tool);
    }

    @Override
    public List<Tool> getUserToolList(Long userId) {
        return toolRepository.findAllByUserId(userId);
    }
}
