package farai.xray_image_manager.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Controller
public class ImageController {
    Logger log = LoggerFactory.getLogger(ImageController.class);
    @Autowired
public ImageService imageService;
    @GetMapping("/Images/{patientId}")
    public void getImages(@PathVariable String patientId){}
    //@GetMapping("/Images/{patientId}/{ImageId}") public void getImage(@PathVariable String patientId,@PathVariable String imageId){}

    @PostMapping("/Images/{patientId}")
    public ResponseEntity<String> uploadImage(@PathVariable String patientId, @RequestParam(name = "uploads") MultipartFile [] imagesUploaded){
            imageService.checkIfAnythingUploaded(imagesUploaded,patientId);
            log.info("uploaded to the next function");
       // imageService.checkIfFileFormatSupported(imagesUploaded,"100g");

        return ResponseEntity.ok("submission was a success");
    }
    //@GetMapping("/system") public String getSystemName(){String Osname = System.getProperty("os.name");System.out.println();System.out.println("The Operating System is "+Osname);return Osname ;
}
