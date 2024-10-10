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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("submit")
    public String submit(){
        return "submit/submit.html";
    }
    @GetMapping("search")
    public String search(){
        return "search/search.html";
    }
    @GetMapping("create-profile")
    public String createProfile(){
        return "create-profile/create-profile.html";
    }
    @GetMapping("dashboard")
    public String dashboard(Model model){
        String url = "http://localhost:800/patients/1";
        List<?> patients = restTemplate.getForObject(url,List.class);
        model.addAttribute("patients",patients);
        logs.info("patients: {}",patients);

        return "dashboard/dashboard.html";
    }
}
