package pl.coderslab.diywithspring.services;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.UserDetails;

public interface UserDetailsService {

    void saveUserDetails(UserDetails userDetails);
    UserDetails getUserDetails(Long userId);
    Byte[] getByteAvatar(MultipartFile file);
}
