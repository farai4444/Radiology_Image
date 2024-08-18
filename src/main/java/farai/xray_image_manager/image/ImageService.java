package farai.xray_image_manager.image;

import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class ImageService {
    @Autowired
    public ImageRepo imageRepo;
    Logger log = LoggerFactory.getLogger(ImageService.class);

    public void checkIfAnythingUploaded(MultipartFile [] imageFiles, String patientId) {
        if (imageFiles.length == 0)
            log.info("There is nothing uploaded because the provided files are empty");
        else
            checkIfImageExceedSize(imageFiles,patientId);
    }
    public void checkIfImageExceedSize(MultipartFile [] imageFiles, String patientId){
        for (MultipartFile imageFile:imageFiles){
            long imageSize = (long) (imageFile.getSize()/Math.pow(1024, 2));
            if (imageSize>10){log.info("This file "+imageFile.getOriginalFilename()+"of size "+imageSize+" is too large for the required size");}
            else {checkIfFileFormatSupported(imageFiles,patientId);}
        }
    }
    public void checkIfFileFormatSupported(MultipartFile [] imageFiles,String patientId ){
        for (MultipartFile imageFile:imageFiles){
          //  boolean fileName = imageFile.getOriginalFilename().endsWith(".jpg");
         //  System.out.println(fileName);

            if (!imageFile.getOriginalFilename().endsWith("jpg")){log.info("This is not the correct required file format .jpg");
            }
            else {
                imageDirectory(patientId,imageFile);
                ///imageRepo.storeImage(imageDirectory(patientId,imageFile));
               // log.debug("worked");log.error("it worked" );log.info("mmmehh");
            }

        }
    }
    public void imageDirectory(String patientId,MultipartFile imageFile){
        Path imageDirectoryPath = FileSystems.getDefault().getPath("/Users","/Public/Radiology/"+ patientId);
        Path imageFilePath = FileSystems.getDefault().getPath("/Users","/Public/Radiology/"+ patientId+"/"+imageFile.getOriginalFilename());
        try {
            if (Files.exists(imageDirectoryPath)&&Files.isDirectory(imageDirectoryPath)) {
                log.info("This directory already exist");
            }
            else {
                Files.createDirectories(imageDirectoryPath);
                if (Files.exists(imageDirectoryPath)&&Files.isDirectory(imageDirectoryPath)) {
                    log.info("The image directory did not exist, hence it is now created");
                }
                else {log.info("the directory was not created try uploading images again to resolve this");}
            }
            if(!Files.exists(imageFilePath)) {
                imageRepo.storeImage(imageFilePath,imageFile);
             //   log.info("the file is now created");
            }
            else{log.info("This File already exist, the process of creating the file has been cancelled");}
        } catch (FileAlreadyExistsException e) {
            log.info("the file you are trying to upload already exits ",e);
            throw new RuntimeException(e);
        } catch (IOException e) {log.error("The image directory was not created here is why ",e);
            throw new RuntimeException(e);}
        catch (UnsupportedOperationException | SecurityException e){log.error("the operation you are trying to execute is not supported");
            throw new RuntimeException("THERE IS AN UNSUPPORTED OPERATION OR SECURITY CONFLICT ENCOUNTERED CHECK FROM THIS ",e);}
    }
    public Path checkTheOperatingSystem(Path imageDirectoryPath){
        Path directoryPath = null;
        return directoryPath;
    }
    public Path specifyImageFilePath(FileSystems fileSystems){
        Path imageFilePath = null;

        return imageFilePath;
    }

}

