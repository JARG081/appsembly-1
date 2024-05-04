package appsembly.appsembly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    @SuppressWarnings("null")
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3001")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization")
                .allowCredentials(true)
                .maxAge(3600);
        // registry.addMapping("/auth/**")
        // .allowedOrigins("http://localhost:5173")
        // .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        // .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization")
        // .allowCredentials(false)
        // .maxAge(3600);

    }
}
