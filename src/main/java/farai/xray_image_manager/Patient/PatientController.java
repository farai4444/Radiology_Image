package farai.xray_image_manager.Patient;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/patient")
@Secured("USER")
public class PatientController {
    @Autowired
    public PatientService patientService;
    @Autowired
    public OtherService otherService;
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

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
    public String tempSearchPatient(@RequestParam(required = false) String patientId,@RequestParam(required = false) String username, Model model) {
        if (patientId != null || username != null) {
         List<?> searchResults = patientService.findByPatientId(patientId);
         model.addAttribute("searchResults",searchResults);
         model.addAttribute("username",username);
         model.addAttribute("usernameParam",username);
        }
        return "search/search.html";
        //return "redirect:/search";
    }
    // if statement needs to be reviewed, there is no processing of null object and the return value is the same
    @PostMapping("/create-profile")
    public String createProfile(@RequestParam(required = false) String username,@RequestParam(name ="patientName") String patientName,@RequestParam(name ="patientSurname") String patientSurname,@RequestParam(name ="country") String country,@RequestParam(name ="city") String city,@RequestParam(name ="address") String address,@RequestParam(name ="age") int age, @RequestParam(name ="gender") char gender,Model model) {
        Patient patient = new Patient(patientName,patientSurname,address,country,city,age,gender);
        log.info("the id is "+patient.getPatientId());
        if (otherService.validateInput(patient)==1) {
            if (username != null) {
                log.info("patientId is " + patient.getPatientId());
                model.addAttribute("info", "The patient created has Id: " + patient.getPatientId());
                model.addAttribute("username",username);
                model.addAttribute("usernameParam",username);
            }

            return "/search/search.html";
        }
        else {
            if (username != null) {
                String error0 = "Please reenter your details";
                model.addAttribute("error0", error0);
                model.addAttribute("username",username);
            }
            return "create-profile/create-patient-profile.html";
        }
    }

}