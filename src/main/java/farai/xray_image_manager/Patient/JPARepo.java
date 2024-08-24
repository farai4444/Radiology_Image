package farai.xray_image_manager.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPARepo   extends JpaRepository<Patient, String> {

}
