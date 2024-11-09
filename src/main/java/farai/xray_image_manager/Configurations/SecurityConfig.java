package farai.xray_image_manager.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
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
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/sign-in","/sign-up","/css/**","/js/**","/img/**","/user/**").permitAll().anyRequest().authenticated());
        //http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(form -> form.loginPage("/sign-in").defaultSuccessUrl("/search",true).failureUrl("/signIn?error")).csrf(AbstractHttpConfigurer::disable).sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)// Allow session creation
        ).rememberMe(rememberMe -> rememberMe.key("myuniqueValue").tokenValiditySeconds(86400));
        return http.build();
    }
}
