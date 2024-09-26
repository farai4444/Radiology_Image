package farai.xray_image_manager.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    public ImageRepo imageRepo;
    Logger log = LoggerFactory.getLogger(ImageService.class);

    public void checkIfAnythingUploaded(MultipartFile [] imageFiles, int patientId, String uploader) {
        if (imageFiles.length == 0)
            log.info("There is nothing uploaded because the provided files are empty");
        else
            checkIfImageExceedSize(imageFiles,patientId,uploader);
    }
    public void checkIfImageExceedSize(MultipartFile [] imageFiles, int patientId, String uploader){
        for (MultipartFile imageFile:imageFiles){
            long imageSize = (long) (imageFile.getSize()/Math.pow(1024, 2));
            if (imageSize>10){log.info("This file "+imageFile.getOriginalFilename()+"of size "+imageSize+" is too large for the required size");}
            else {checkIfFileFormatSupported(imageFiles,patientId,uploader);}
        }
    }
    public void checkIfFileFormatSupported(MultipartFile [] imageFiles, int patientId, String uploader){
        for (MultipartFile imageFile:imageFiles){
          //  boolean fileName = imageFile.getOriginalFilename().endsWith(".jpg");
         //  System.out.println(fileName);

            if (!imageFile.getOriginalFilename().endsWith("jpg")){log.info("This is not the correct required file format .jpg");
            }
            else {
                imageDirectory(patientId,imageFile,uploader);
                ///imageRepo.storeImage(imageDirectory(patientId,imageFile));
               // log.debug("worked");log.error("it worked" );log.info("mmmehh");
            }

        }
    }
    public void imageDirectory(int patientId, MultipartFile imageFile, String uploader){
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
                imageRepo.storeImage(imageFilePath,imageFile,patientId,uploader);
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
    public List<String> imagesStream(int patientId){
      return imageRepo.retrieveImages(patientId);
    }
    public Resource imageStream(int patientId, String imageName){
        return  imageRepo.retrieveImage(patientId,imageName);
    }
    public Resource imageStreamDB(URI uri){
        return  imageRepo.retrieveImageDB(uri);
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

