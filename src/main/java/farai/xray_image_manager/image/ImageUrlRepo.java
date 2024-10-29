package farai.xray_image_manager.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.net.URI;
import java.util.List;


public interface ImageUrlRepo extends JpaRepository<ImageUrl,Integer> {
    @Query("SELECT url FROM ImageUrl WHERE url_Id=?1")
    String findUrlByUrlId(int url_Id);
    @Query("SELECT url_Id FROM ImageUrl WHERE patient_Id=?1")
    List<?> findUrlIdByPatientId(int patientId);
    //public Optional<ImageUrl> findUrlByUrlId(int url_Id);
    @Query("SELECT i FROM ImageUrl i  WHERE patient_Id=?1")
    List<?> findAllByPatientId(int patientId);
}
