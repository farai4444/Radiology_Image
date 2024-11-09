package farai.xray_image_manager.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    Sys_user user;
    @Autowired
    UserService userService;
    @PostMapping("/create-profile")
    public ResponseEntity<String> createUser(@RequestParam(name="name") String name,@RequestParam(name = "surname") String surname,@RequestParam(name = "username") String username,@RequestParam(name = "password") String password,@RequestParam(name = "email") String email){
        user = new Sys_user(name,surname,email,username,userService.encrypt(password));
        userService.setUserProfile(user);
        return ResponseEntity.ok("The following user has been created" + user.toString());
    }
    // this only checks both the password and username but not individual values
    @PostMapping
    public String getUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,@RequestParam(required = false) String error, Model model){
        user.setUsername(username);
        user.setPassword(userService.encrypt(password));
        switch (userService.isUser(user)){
            case 1 -> {
                model.addAttribute("username",user.getUsername());
                log.info(user.toString());
                log.info("sign in was successful");
                return "/search/search.html";
            }
            case 0 ->{
                if (error!=null) {
                    log.info("error has been executed");
                    model.addAttribute("error", "please check if you entered the correct credentials");
                    return "/sign-in/signIn.html";
                }
            }
        }
        model.addAttribute("error","Credentials are invalid please create and account");
        log.info("this is the default return error");
        return "/sign-in/signIn.html";
    }

}
