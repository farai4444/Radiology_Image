package farai.xray_image_manager.Configurations;

import jakarta.servlet.ServletContext;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
@Component
public class ServletBeans implements ApplicationListener<ApplicationStartedEvent> {
   private ServletContext servletContext;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        this.servletContext = ((WebApplicationContext)
                applicationContext).getServletContext();

    }
}
