package farai.xray_image_manager.Configurations;

import farai.xray_image_manager.User.Sys_user;
import farai.xray_image_manager.User.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
private UserRepo userRepo;
private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Sys_user user = userRepo.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException(username+" not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }



}
