package farai.xray_image_manager.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
public class ImageController {
    Logger log = LoggerFactory.getLogger(ImageController.class);
    @Autowired
public ImageService imageService;
    @Autowired
    public ImageUrlService imageUrlService;
    @GetMapping("/Images/{patientId}")
    public ResponseEntity<List<String>> getImages(@PathVariable int patientId){
    return ResponseEntity.ok(imageService.imagesStream(patientId));
    }
    /*Accessing images using file system*/
    @GetMapping("/Images/{patientId}/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable int patientId, @PathVariable String imageName){
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"" +imageService.imageStream(patientId, imageName).getFilename()+"\"").body(imageService.imageStream(patientId, imageName));
        try {
            //imageService.imageStream(patientId,imageName).getFile().toPath()
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"image/jpeg").body(Files.readAllBytes(imageService.imageStream(patientId,imageName).getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*Accessing images using Database*/

    @GetMapping("/Images")
    public ResponseEntity<byte[]> getImageByDB(@RequestParam("url_Id") int url_Id){
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"" +imageService.imageStream(patientId, imageName).getFilename()+"\"").body(imageService.imageStream(patientId, imageName));
        try {
            //imageService.imageStream(patientId,imageName).getFile().toPath()
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"image/jpeg").body(Files.readAllBytes(imageService.imageStreamDB(imageUrlService.getUrl(url_Id)).getFile().toPath()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/Images")
    public ResponseEntity<String> uploadImage(@RequestParam("patientId") int patientId, @RequestParam("uploader") String uploader, @RequestParam(name = "uploads") MultipartFile [] imagesUploaded){
            imageService.checkIfAnythingUploaded(imagesUploaded,patientId,uploader);
           /// log.info("uploaded to the next function");
       // imageService.checkIfFileFormatSupported(imagesUploaded,"100g");

        return ResponseEntity.ok("The images are uploaded for patient "+patientId);
    }
    @GetMapping("/system")
    public ResponseEntity<String> getSystemName(){String Osname = System.getProperty("os.name");System.out.println();System.out.println("The Operating System is "+Osname);return ResponseEntity.ok(Osname);
}
}
