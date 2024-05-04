package appsembly.appsembly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import appsembly.appsembly.services.models.validation.UserValidation;

@Configuration
public class ValidationsConfig {
    @Bean
    public UserValidation userValidation() {
        return new UserValidation();
    }
}
