package pl.coderslab.diywithspring.services;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.Tool;

import java.util.List;

public interface ToolService {

    void saveTool(Tool tool);
    List<Tool> getUserToolList(Long userId);
    Byte[] getByteImage(MultipartFile file);
    Tool getToolByID(Long toolId);
    void deleteToolById(Long toolId);

}
