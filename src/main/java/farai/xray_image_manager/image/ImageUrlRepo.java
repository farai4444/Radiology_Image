package farai.xray_image_manager.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.net.URI;


public interface ImageUrlRepo extends JpaRepository<ImageUrl,Integer> {
    @Query("SELECT url FROM ImageUrl WHERE url_Id=?1")
    String findUrlByUrlId(int url_Id);
    //public Optional<ImageUrl> findUrlByUrlId(int url_Id);
}
