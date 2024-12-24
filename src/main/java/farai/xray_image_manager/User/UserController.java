package farai.xray_image_manager.User;

import farai.xray_image_manager.Configurations.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    Sys_user user;
    @Autowired
    UserService userService;

    @PostMapping("/create-profile")
    public String createUser(@RequestParam(name="name") String name,@RequestParam(name = "surname") String surname,@RequestParam(name = "username") String username,@RequestParam(name = "password") String password,@RequestParam(name = "email") String email,Model model){
        user = new Sys_user(name,surname,email,username,userService.encrypt(password),"USER_ROLE");
        userService.setUserProfile(user);
        return "redirect:/sign-in?created";
    }
    // this only checks both the password and username but not individual values
    @PostMapping
    public String getUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpSession session){
        user.setUsername(username);
        user.setPassword(password);
       int valueValidation = userService.validation(user);
       log.info("the validation value is: "+valueValidation);
      switch (valueValidation){
            case 1 -> {
                try {
                    log.info("user validation was successful");
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    //Fix the GRANTED AUTHORITIES IN THE USER CLASS AND IN THE CUSTOM USER DETAILS SERVICE
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),authorities);
                    Authentication authentication = authenticationManager.authenticate(token);
                    context.setAuthentication(token);
                   SecurityContextHolder.setContext(context);
                    log.info("Authentication is: " +  authentication.toString());
                    SecurityContext nowAuth = SecurityContextHolder.getContext();
                    log.info("The Security Context Status is: "+ nowAuth.toString());
                   if (authentication.isAuthenticated()){
                       session.setAttribute("SPRING_SECURITY_CONTEXT", nowAuth);
                       log.info("authentication was a success");return "redirect:/search";}
                  else { log.info("authentication failed"); return "redirect:/sign-in?invalid";}
                } catch (Exception e){log.info("log in failed: " + e.getMessage()); return "redirect:/sign-in?invalid";}
            }
            case 0 ->{
                log.info("The credentials provided are invalid");
                log.info("error has been executed");
            }
        }
        return "redirect:/sign-in?invalid";
    }

}
