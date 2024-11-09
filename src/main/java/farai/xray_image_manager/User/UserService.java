package farai.xray_image_manager.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
   private UserRepo userRepo;
   private BCryptPasswordEncoder encoder;
   private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService() {}

    //This function returns the encrypted value
    public String encrypt(String password) {
       /* String salt =saltRandGen();
        int iterations = 10000;
        int keyLength =256;
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("")
        */
        encoder = new BCryptPasswordEncoder();
        String hashPassword = encoder.encode(password);
        log.info(hashPassword);
        return hashPassword;
    }
    //This function validates the encrypted value and the string value
    public boolean isValidEncrypt(String password, String encryption){
        encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encryption);
    }

    // This function return either 1 or 0
    public int isUser(Sys_user user){
        if (userRepo.existsByObject(user)){
            log.info("Existence check was successful");
            return 1;
        }
        else {
        return 0;}
    }
    // this function must return a response value
    public void setUserProfile(Sys_user user){
        //Example<User> userExample = Example.of(user);
        switch (isUser(user)) {
            case 1 -> {
                log.info("user profile already created");
            }
            case 0 -> {
                userRepo.save(user);
                log.info("user successful creation");
            }
        }
    }
    @Transactional(readOnly = true)
    public Optional<?> getUser(Sys_user user){
        Example<Sys_user> example = Example.of(user);
        return userRepo.findOne(example);
    }


}
