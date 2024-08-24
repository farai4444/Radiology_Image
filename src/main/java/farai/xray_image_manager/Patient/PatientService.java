package farai.xray_image_manager.Patient;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    final static String ALL_PATIENT_NATIVE_QUERY = "select patientId, pname, psurname from patient";
    @PersistenceContext
    private EntityManager em;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    JPARepo jpaRepo;
    @Transactional(readOnly = true)
    public List<?> findByPatientId(String patientId) {
        log.error("findByPatientId");
        //return jpaRepo.findByPatientId(patientId);
       Optional<?> searchResult = jpaRepo.findById(patientId);
       return searchResult.stream().toList();
    }

    @Transactional(readOnly = true)
    public List<?> findAllPatients() {
        log.info("This is the list of all the patients in the database");
        //em = entityManagerFactory.createEntityManager();
         //em.createNamedQuery(Patient.FIND_ALL).executeUpdate();
         return jpaRepo.findAll();
    }


    public void savePatient(Patient patient) {
       log.info(patient.getPatientId());
        jpaRepo.save(patient);
        log.info("patient profile has been created");
    }


    public void deletePatient() {
        log.info("info deleted");

    }
}
