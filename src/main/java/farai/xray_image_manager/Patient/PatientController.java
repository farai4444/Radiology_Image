package farai.xray_image_manager.Patient;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    public PatientService patientService;
    @Autowired
    public OtherService otherService;

    @GetMapping("/{patientId}")
    public ResponseEntity<List<?>> getPatient(@PathVariable String patientId) {
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
    @PostMapping("/create-profile")
    public ResponseEntity<?> createProfile(@RequestParam(name ="patientName") String patientName,@RequestParam(name ="patientSurname") String patientSurname,@RequestParam(name ="country") String country,@RequestParam(name ="city") String city,@RequestParam(name ="address") String address,@RequestParam(name ="age") int age, @RequestParam(name ="gender") char gender) {
        otherService.validateInput(patientName,patientSurname,country,city,address,age,gender);
        return ResponseEntity.ok("Patient profile successfully saved");
    }

}