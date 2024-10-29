package farai.xray_image_manager.image;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageUrlService {
    private static final Logger log = LoggerFactory.getLogger(ImageUrlService.class);
    @Autowired
     public ImageUrlRepo imageUrlRepo;
    public  ImageUrl imageUrl;
public void uploadUrl(int patientId, String uploader, String url){
imageUrl = new ImageUrl(patientId,uploader, LocalDateTime.now(),url);
imageUrlRepo.save(imageUrl);
}
public List<?> getUrlIds(int patientId){
return imageUrlRepo.findUrlIdByPatientId(patientId);
}

    public List<?> getImageUrl(int patientId) {
        return imageUrlRepo.findAllByPatientId(patientId);
    }

    public URI getUrl(int url_Id) throws URISyntaxException, MalformedURLException {
   // imageUrl = new ImageUrl(patient_Id);
    return new URI( imageUrlRepo.findUrlByUrlId(url_Id) );
}

}
