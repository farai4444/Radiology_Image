package farai.xray_image_manager.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest extends UserService {
    @Test
    void testEncrypt() {
       System.out.println("This is the encryption length: " +  encrypt("what").length());
       System.out.println(isValidEncrypt("what","$2a$10$5syf9hF5qkVeOzAPKLX3p.dn5JwD.RRiidFVnkDP1ZcDnBbOvy1n."));
    }
}