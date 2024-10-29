package farai.xray_image_manager.Patient;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    public PatientService patientService;
    @Autowired
    public OtherService otherService;

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatient(@PathVariable String patientId) {
        return ResponseEntity.ok( patientService.findByPatientId(patientId));
    }

    @GetMapping
    public ResponseEntity<List<?>> getPatients() {

        return ResponseEntity.ok(patientService.findAllPatients());
    }

    @GetMapping("/search")
    public ResponseEntity<List<?>> searchPatient(@RequestParam(name = "patientId") String patientId) {
        return ResponseEntity.ok(patientService.findByPatientId(patientId));
    }
    @GetMapping("/temp/search")
    public String tempSearchPatient(@RequestParam(required = false) String patientId, Model model) {
        if (patientId != null) {
         List<?> searchResults = patientService.findByPatientId(patientId);
         model.addAttribute("searchResults",searchResults);
        }
        return "search/search.html";
    }
    @PostMapping("/create-profile")
    public ResponseEntity<?> createProfile(@RequestParam(name ="patientName") String patientName,@RequestParam(name ="patientSurname") String patientSurname,@RequestParam(name ="country") String country,@RequestParam(name ="city") String city,@RequestParam(name ="address") String address,@RequestParam(name ="age") int age, @RequestParam(name ="gender") char gender) {
        otherService.validateInput(patientName,patientSurname,country,city,address,age,gender);
        return ResponseEntity.ok("Patient profile successfully saved");
    }

}