package farai.xray_image_manager.User;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    Sys_user user;
    @Autowired
    HttpSession request;
    @Autowired
    UserService userService;
    @PostMapping("/create-profile")
    public String createUser(@RequestParam(name="name") String name,@RequestParam(name = "surname") String surname,@RequestParam(name = "username") String username,@RequestParam(name = "password") String password,@RequestParam(name = "email") String email,Model model){
        user = new Sys_user(name,surname,email,username,userService.encrypt(password),"USER_ROLE");
        userService.setUserProfile(user);
        model.addAttribute("error","account created");
        return "/sign-in/signIn.html";
    }
    // this only checks both the password and username but not individual values
    @PostMapping
    public String getUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model){
        user.setUsername(username);
        user.setPassword(password);
       int valueValidation = userService.validation(user);
     //  log.info("the validation value is: "+valueValidation);
      switch (valueValidation){
            case 1 -> {
                model.addAttribute("username",user.getUsername());
                model.addAttribute("usernameParam",user.getUsername());
                log.info("sign in was successful");
                log.info("The session id: "+request.getId()+"Session info"+request.toString());
                // there is issues with this return value
               return "/search/search.html";
               // return ResponseEntity.status(HttpStatus.FOUND).header("Location","/search").build();
           // return new ModelAndView("redirect:/search");
            }
            case 0 ->{
                log.info("error has been executed");
                model.addAttribute("error", "please check if you entered the correct credentials");
                    return "/sign-in/signIn.html";
               // return ResponseEntity.status(HttpStatus.FOUND).header("Location","/sign-in").build();
           // return  new ModelAndView("redirect:/sign-in");
            }
        }
      //  model.addAttribute("error","Credentials are invalid please create and account");
        //log.info("this is the default return error");


       return "/sign-in/signIn.html";
       // return ResponseEntity.status(HttpStatus.FOUND).header("Location","/sign-in").build();
   // return new ModelAndView("redirect:/sign-in");
    }

}
