package farai.xray_image_manager.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PatientController {

    @GetMapping("/patients/{patientId}")
    public String getPatient(@PathVariable String patientId) {
        return "2256";
    }

    @GetMapping("/patients")
    public String getPatients() {
        return "the patient is Farai";
    }

    @GetMapping("/patients/search")
    public void searchPatient(@RequestParam String patientId) {
    }


}