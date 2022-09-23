package pl.coderslab.diywithspring.services.implemetations;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.repositories.ProjectRepository;
import pl.coderslab.diywithspring.services.ProjectService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
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
                return ArrayUtils.toObject(Files.readAllBytes(Paths.get("src/main/webapp/WEB-INF/resources/static/powertool.jpeg")));
            }

        }catch (IOException e){
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return null;
    }
}
