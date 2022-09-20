package pl.coderslab.diywithspring.services;

import pl.coderslab.diywithspring.models.UserDetails;

public interface UserDetailsService {

    void saveUserDetails(UserDetails userDetails);
    UserDetails getUserDetails(Long userId);

}
