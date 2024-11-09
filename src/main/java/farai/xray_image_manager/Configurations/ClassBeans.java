package farai.xray_image_manager.Configurations;
import farai.xray_image_manager.Patient.OtherService;
import farai.xray_image_manager.Patient.Patient;
import farai.xray_image_manager.Patient.PatientService;
import farai.xray_image_manager.User.Sys_user;
import farai.xray_image_manager.User.UserActivityMetrics;
import farai.xray_image_manager.User.UserService;
import farai.xray_image_manager.image.ImageRepo;
import farai.xray_image_manager.image.ImageService;
import farai.xray_image_manager.image.ImageUrl;
import farai.xray_image_manager.image.ImageUrlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class ClassBeans {

    @Bean
    public ImageService imageService () { return new ImageService();}
    @Bean
    public ImageRepo imageRepo () {return new ImageRepo();}
    @Bean
    public ImageUrl imageUrl () {return new ImageUrl();}
    @Bean
    public ImageUrlService imageUrlService () {return new ImageUrlService();}
    @Bean
    public Patient patient () {return new Patient();}
    @Bean
    public PatientService patientRepo () {return new PatientService();}
    @Bean
    public OtherService otherService () {return new OtherService();}
    @Bean
    public RestTemplate restTemplate () {return new RestTemplate();}
    @Bean
    public Sys_user user () {return new Sys_user();}
    @Bean
    public UserActivityMetrics userActivityMetrics () {return new UserActivityMetrics();}
    @Bean
    public UserService userService () {return new UserService();}

}
