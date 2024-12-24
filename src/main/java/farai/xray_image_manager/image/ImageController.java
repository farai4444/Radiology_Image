package farai.xray_image_manager.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/image")
@Secured("USER")
public class ImageController {
    Logger log = LoggerFactory.getLogger(ImageController.class);
    @Autowired
public ImageService imageService;
    @Autowired
    public ImageUrlService imageUrlService;
/*ADD AN API THAT GETS URL_ID USING PATIENT_ID*/
    /*first get image names by patientId using this API*/
    @GetMapping("/name/{patientId}")
    public ResponseEntity<List<String>> getImageNames(@PathVariable int patientId){
    return ResponseEntity.ok(imageService.imagesStream(patientId));
    }
    /*this API gets the image urls that will be passed to the getImageByDB method for display on interface*/
    @GetMapping("/file/id/{patientId}")
    public ResponseEntity<List<?>> getImageFileUrlId(@PathVariable  int patientId){
        return ResponseEntity.ok(imageUrlService.getUrlIds(patientId));
    }
    @GetMapping("/info/url/{patientId}")
    public ResponseEntity<List<?>> getImageUrl(@PathVariable int patientId){
        return ResponseEntity.ok(imageUrlService.getImageUrl(patientId));
    }
    /*Accessing images using file system*/
    /*use this API to display images on interfaces taking the image names from getImageNames()*/
    @GetMapping("/file/{patientId}/{imageName}")
    public ResponseEntity<byte[]> getImageFIleByFS(@PathVariable int patientId, @PathVariable String imageName){
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"" +imageService.imageStream(patientId, imageName).getFilename()+"\"").body(imageService.imageStream(patientId, imageName));
        try {
            //imageService.imageStream(patientId,imageName).getFile().toPath()
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"image/jpeg").body(Files.readAllBytes(imageService.imageStream(patientId,imageName).getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*Accessing images file using Database*/
    /*first get the url_Id using parameter patientId from method getImageFileUrlId()*/
    @GetMapping("/file")
    public ResponseEntity<byte[]> getImageFileByDB(@RequestParam("url_Id") int url_Id){
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"" +imageService.imageStream(patientId, imageName).getFilename()+"\"").body(imageService.imageStream(patientId, imageName));
        try {
            //imageService.imageStream(patientId,imageName).getFile().toPath()
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"image/jpeg","").body(Files.readAllBytes(imageService.imageStreamDB(imageUrlService.getUrl(url_Id)).getFile().toPath()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping
    public ResponseEntity<String> uploadImageFIle(@RequestParam("patientId") int patientId, @RequestParam("uploader") String uploader, @RequestParam(name = "uploads") MultipartFile [] imagesUploaded){
            imageService.checkIfAnythingUploaded(imagesUploaded,patientId,uploader);
           /// log.info("uploaded to the next function");
       // imageService.checkIfFileFormatSupported(imagesUploaded,"100g");
        return ResponseEntity.ok("The images are uploaded for patient "+patientId);
    }
    @PostMapping("/upload")
    public String uploadImageFIleTemp(@RequestParam(required = false) String username,@RequestParam("patientId") int patientId, @RequestParam(name = "uploader") String uploader, @RequestParam(name = "uploads") MultipartFile [] imagesUploaded, Model model){
        imageService.checkIfAnythingUploaded(imagesUploaded,patientId,uploader);
        /// log.info("uploaded to the next function");
        // imageService.checkIfFileFormatSupported(imagesUploaded,"100g");
        if (username != null) {
            model.addAttribute("info", "images uploaded");
            model.addAttribute("username",username);
            model.addAttribute("usernameParam",username);
        }
        return "/search/search.html";
    }
    @GetMapping("/system")
    public ResponseEntity<String> getSystemName(){String Osname = System.getProperty("os.name");System.out.println();System.out.println("The Operating System is "+Osname);return ResponseEntity.ok(Osname);
}
}
