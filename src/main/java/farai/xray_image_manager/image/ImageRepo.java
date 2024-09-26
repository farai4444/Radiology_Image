package farai.xray_image_manager.image;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageRepo {
    Logger log = LoggerFactory.getLogger(ImageRepo.class);
    @Autowired
     public ImageUrlService imageUrlService;


    public void storeImage(Path directoryPath, MultipartFile imageFile,int patientId,String uploader){
            try {
                imageFile.transferTo(directoryPath);
                String url = getImageFilePath(patientId, imageFile.getOriginalFilename()).toString();
                imageUrlService.uploadUrl(patientId, uploader, url);
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
    public List<String> retrieveImages(int patientId){
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
    public Resource retrieveImage(int patientId,String imageName){
        Resource imageResource = null;
        try {
            imageResource = new UrlResource(getImageFilePath(patientId, imageName));
        } catch (MalformedURLException e) {
            log.info("You have passed an invalid Url");
            throw new RuntimeException(e);
        }
        return imageResource;
    }
    public Resource retrieveImageDB(URI uri){
        Resource imageResource = null;
        try {
            imageResource = new UrlResource(uri);
        } catch (MalformedURLException e) {
            log.info("You have passed an invalid Url");
            throw new RuntimeException(e);
        }
        return imageResource;
    }
    public Path getImageDirectoryPath(int patientId){
        return FileSystems.getDefault().getPath("/Users","/Public/Radiology/"+ patientId);}

    public URI getImageFilePath(int patientId, String imageName){
        return FileSystems.getDefault().getPath("/Users","/Public/Radiology/"+ patientId+"/"+imageName).toUri();}

}


