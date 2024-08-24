package farai.xray_image_manager.image;

import farai.xray_image_manager.Patient.PatientService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageUrlService {
    private static final Logger log = LoggerFactory.getLogger(ImageUrlService.class);
    @Autowired
    ImageUrlRepo imageUrlRepo;


}
