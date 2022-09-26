package pl.coderslab.diywithspring.services.implemetations;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.Tool;
import pl.coderslab.diywithspring.repositories.ToolRepository;
import pl.coderslab.diywithspring.services.interfaces.ToolService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
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

    @Override
    public Byte[] getByteImage(MultipartFile file){

        try{
            if(Objects.nonNull(file)){
                Byte[] byteObject = new Byte[file.getBytes().length];
                int i=0;

                for (byte b : file.getBytes()){
                    byteObject[i++]=b;
                }
                return byteObject;
            }else {
                return ArrayUtils.toObject(Files.readAllBytes(Paths.get("/resources/images/powertool.jpeg")));
            }

        }catch (IOException e){
            log.error("Error occurred", e);
            e.printStackTrace();
        }
//        nowy wyjątek
        return null;
    }

    @Override
    public Tool getToolByID(Long toolId) {
        return toolRepository.findById(toolId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteToolById(Long toolId) {
        toolRepository.deleteById(toolId);
    }
}
