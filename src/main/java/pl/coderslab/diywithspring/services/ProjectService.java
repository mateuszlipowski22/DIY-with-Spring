package pl.coderslab.diywithspring.services;

import pl.coderslab.diywithspring.models.Project;

import java.util.List;

public interface ProjectService {

    void saveProject(Project project);
    Project findProjectByID(Long id);
    List<Project> findProjectsByUserID(Long id);
    List<Project> findAll();

}
