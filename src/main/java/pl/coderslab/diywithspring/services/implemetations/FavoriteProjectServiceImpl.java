package pl.coderslab.diywithspring.services.implemetations;

import org.springframework.stereotype.Service;
import pl.coderslab.diywithspring.models.FavoriteProject;
import pl.coderslab.diywithspring.models.Project;
import pl.coderslab.diywithspring.repositories.FavoriteProjectRepository;
import pl.coderslab.diywithspring.repositories.ProjectRepository;
import pl.coderslab.diywithspring.repositories.UserRepository;
import pl.coderslab.diywithspring.services.interfaces.FavoriteProjectService;

import java.util.List;

@Service
public class FavoriteProjectServiceImpl implements FavoriteProjectService {

    private final FavoriteProjectRepository favoriteProjectRepository;

    public FavoriteProjectServiceImpl(FavoriteProjectRepository favoriteProjectRepository) {
        this.favoriteProjectRepository = favoriteProjectRepository;
    }

    @Override
    public void saveFavoriteProject(FavoriteProject favoriteProject) {
        favoriteProjectRepository.save(favoriteProject);
    }

    @Override
    public List<FavoriteProject> findFavoriteProjectsByUserID(Long userID) {
       return favoriteProjectRepository.findAllByUserId(userID);
    }

    @Override
    public void deleteFavoriteProject(FavoriteProject favoriteProject) {
        favoriteProjectRepository.delete(favoriteProject);
    }

    @Override
    public FavoriteProject findFavoriteProjectByID(Long favoriteProjectID) {
        return favoriteProjectRepository.findById(favoriteProjectID).orElseThrow(IllegalArgumentException::new);
    }
}
