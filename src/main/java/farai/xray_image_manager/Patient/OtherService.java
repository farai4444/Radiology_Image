package farai.xray_image_manager.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class OtherService {
    @Autowired
    JPARepo jpaRepo;
    @Autowired
    PatientService patientService;
    private static final Logger log = LoggerFactory.getLogger(OtherService.class);

    public int validateInput(Patient patient) {

       // boolean ifExist = jpaRepo.existsById(patient.getPatientId());
       // if (!ifExist){
            log.info("Patient does not exist");
            patientService.savePatient(patient);
            return 1;
       // }
      //  else {
         //   log.info("Patient Exist hence no profile will be created");
      //  return 0;
     //   }
    }
}
