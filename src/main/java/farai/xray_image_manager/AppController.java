package farai.xray_image_manager;

import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {
    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        List<String> names;
        names = List.of("Levin","Devin","Kevin","Revin");
        return ResponseEntity.ok( "farai name");}
    @Autowired
    private DataSource dataSource;
    //private DataSource appSource;
    JdbcTemplate jdbcTemplate;

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
}
