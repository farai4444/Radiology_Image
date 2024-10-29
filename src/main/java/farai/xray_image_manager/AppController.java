package farai.xray_image_manager;

import farai.xray_image_manager.Patient.Patient;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class AppController {
    private static final Logger logs = LoggerFactory.getLogger(AppController.class);
    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        List<String> names;
        names = List.of("Levin","Devin","Kevin","Revin");
        return ResponseEntity.ok( "farai name");}
    @Autowired
    private DataSource dataSource;
    //private DataSource appSource;
    JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("check")
    public ResponseEntity checkDatabaseConnection() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        int res = jdbcTemplate.queryForObject("SELECT 1",Integer.class);
        ResponseEntity result;
        try (Connection connection = dataSource.getConnection()) {
            result = ResponseEntity.ok("Database connection successful! " + connection + " Result Test: "+ res);
        } catch (SQLException e) {
            result = ResponseEntity.ok("Failed to connect to the database: " + e.getMessage());
        }
        return result;
    }

    @GetMapping("upload")
    public String upload(@RequestParam("patientId") int patientId, Model model){
        model.addAttribute("patientId",patientId);
        return "upload/uploads.html";
    }
    @GetMapping("search")
    public String search(){
        return "search/search.html";
    }
    @GetMapping("create-profile")
    public String createProfile(){
        return "create-profile/create-patient-profile.html";
    }
    @GetMapping("dashboard")
    public String dashboard(Model model){
        String url = "http://localhost:800/patients/1";
        List<?> patients = restTemplate.getForObject(url,List.class);
        model.addAttribute("patients",patients);
        logs.info("patients: {}",patients);
        return "dashboard/dashboard.html";
    }
    @GetMapping("test")
    public ResponseEntity.HeadersBuilder<?> test(){
        return  ResponseEntity.noContent();
    }
    @GetMapping("image-gallery")
    public String image_Gallery(@RequestParam("patientId") String patientId,Model model){
        String patientObjectUrl = "http://localhost:800/patient/search?patientId="+patientId;
        /*THE IMAGE URL HAS NOT YET BEEN PLACED*/
        /*CHOOSE BETWEEN DATABASE AND FILESYSTEM URLS*/
        String imageUrl = "http://localhost:800/image/info/url/"+patientId;
      //  String imageUrlObject = "http://localhost:800/image/info/url/"+patientId;/*spot*/
        List<?> response0 = restTemplate.getForObject(patientObjectUrl, List.class);
      //  List<?> response1 = restTemplate.getForObject(imageUrlObject, List.class);
        List<?> response2 = restTemplate.getForObject(imageUrl, List.class);
        model.addAttribute("response0",response0);
       // model.addAttribute("response1",response1);/*x*/
        model.addAttribute("response2",response2);
        return "image-gallery/image-gallery.html";
    }
    @GetMapping("sign-in")
    public String sign_In(){
        return "sign-in/signIn.html";
    }
    @GetMapping("sign-up")
    public String sign_Up(){
        return "sign-up/signup.html";
    }
}
