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

    public void validateInput(String patientName, String patientSurname, String country, String city, String address, int age, char gender) {
        Patient patient = new Patient(patientName,patientSurname,address,country,city,age,gender);
        boolean ifExist = jpaRepo.existsById("0");
        if (!ifExist){
            log.info("Patient does not exist");
            patientService.savePatient(patient);
        }
        else {log.info("Patient Exist hence no profile will be created");}}
}
