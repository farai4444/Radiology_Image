package farai.xray_image_manager.image;


import org.apache.tomcat.util.file.ConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.*;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<String> retrieveImages(String patientId){
        List<String> resouces = new ArrayList<>();
        try {
            if (Files.exists(getImageDirectoryPath(patientId))) {
                Files.walk(Path.of(getImageDirectoryPath(patientId).toUri())).filter(Files::isRegularFile).forEach(path -> resouces.add(path.getFileName().toString()));
                log.info("The images were retrieved for patient Identification"+patientId);
            }
            else
                log.info("The folder does not exist");
        } catch (IOException e) {
            log.error("the images were not retrieved here is why ",e);
            throw new RuntimeException(e);
        }
        return resouces;
    }
    public Resource retrieveImage(String patientId,String imageName){
        Resource imageResource = null;
        try {
            imageResource = new UrlResource(getImageFilePath(patientId, imageName));
        } catch (MalformedURLException e) {
            log.info("You have passed an invalid Url");
            throw new RuntimeException(e);
        }
        return imageResource;
    }
    public Path getImageDirectoryPath(String patientId){
        return FileSystems.getDefault().getPath("/Users","/Public/Radiology/"+ patientId);}

    public URI getImageFilePath(String patientId, String imageName){
        return FileSystems.getDefault().getPath("/Users","/Public/Radiology/"+ patientId+"/"+imageName).toUri();}

}


