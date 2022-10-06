package pl.coderslab.diywithspring.services.interfaces;

import pl.coderslab.diywithspring.models.FavoriteProject;
import pl.coderslab.diywithspring.models.Project;

import java.util.List;

public interface FavoriteProjectService {

    void saveFavoriteProject(FavoriteProject favoriteProject);
    List<FavoriteProject> findFavoriteProjectsByUserID(Long userID);
    void deleteFavoriteProject(FavoriteProject favoriteProject);
    FavoriteProject findFavoriteProjectByID(Long favoriteProjectID);
}
