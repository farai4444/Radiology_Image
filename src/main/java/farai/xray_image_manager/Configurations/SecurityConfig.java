package farai.xray_image_manager.Configurations;

import jakarta.websocket.Endpoint;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        /********change the security to block all requests********/
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("image/**","patient/**","/sign-in","/sign-up","/css/**","/js/**","/img/**","/user/**").permitAll().anyRequest().permitAll());
        //http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(form -> form.loginPage("/sign-in").defaultSuccessUrl("/search",true).failureUrl("/signIn?error")).csrf(AbstractHttpConfigurer::disable).sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        // Allow session creation
        ).rememberMe(rememberMe -> rememberMe.key("myuniqueValue").tokenValiditySeconds(86400));
        return http.build();
    }

}
