package farai.xray_image_manager.Configurations;

import farai.xray_image_manager.image.ImageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LoggerFactory.getLogger(ImageController.class);
    @Order(0)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        log.info("The file exceeds the max size required");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Maximum upload size exceeded. Please try again with a file below 10MB.");
    }
}
