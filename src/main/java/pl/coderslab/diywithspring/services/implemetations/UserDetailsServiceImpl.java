package pl.coderslab.diywithspring.services.implemetations;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.diywithspring.models.UserDetails;
import pl.coderslab.diywithspring.repositories.UserDetailsRepository;
import pl.coderslab.diywithspring.services.UserDetailsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails getUserDetails(Long userId) {
        return userDetailsRepository.findByUserId(userId);
    }

    @Override
    public Byte[] getByteAvatar(MultipartFile file){

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
