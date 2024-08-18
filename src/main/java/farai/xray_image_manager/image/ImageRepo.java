package farai.xray_image_manager.image;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Time;
import java.time.Instant;
import java.util.Date;

import static java.util.Date.from;

@Repository
public class ImageRepo {
    Logger log = LoggerFactory.getLogger(ImageRepo.class);
    public void storeImage(Path directoryPath,MultipartFile imageFile){
            try {
                imageFile.transferTo(directoryPath);
                log.info("The image is created");
            }
            catch (NoSuchFileException e) {
                log.error("you are seing the error because of this", e);
                throw new RuntimeException(e);
            }
            catch (IOException e) {log.error("The image was not created here is why ",e);
                throw new RuntimeException(e);}
            catch (UnsupportedOperationException | SecurityException e){log.error("the operation you are trying to execute is not supported here is why",e);
                throw new RuntimeException("THERE IS AN UNSUPPORTED OPERATION OR SECURITY CONFLICT ENCOUNTERED CHECK FROM THIS ",e);}
    }

}
