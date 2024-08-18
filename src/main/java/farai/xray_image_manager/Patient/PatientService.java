package farai.xray_image_manager.Patient;

import java.util.List;

public class PatientService {
    public void retrievePatient(String patientId){int verifyExistence = checkIfPatientExist(patientId);

        switch (verifyExistence) {
            case 0 -> {

            }
            case 1 ->{}
        };
    }
    public void retrievePatients(){}

    public int checkIfPatientExist(String patientId){return 0;}
    public void deletePatient(){}
    public void modifyPatient(){}

}
