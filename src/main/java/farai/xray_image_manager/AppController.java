package farai.xray_image_manager;

import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AppController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        List<String> names;
        names = List.of("Levin","Devin","Kevin","Revin");
        return ResponseEntity.ok( "farai name");}
    @Autowired
    private DataSource dataSource;

    @GetMapping("/check")
    public ResponseEntity checkDatabaseConnection() {
        ResponseEntity result;
        try (Connection connection = dataSource.getConnection()) {
            result = ResponseEntity.ok("Database connection successful! " + connection);
        } catch (SQLException e) {
            result = ResponseEntity.ok("Failed to connect to the database: " + e.getMessage());
        }
        return result;
    }
    @GetMapping("home/submit")
    public String submit(){
        return "submit/submit.html";
    }
}
