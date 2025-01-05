package com.example.lingochat.rest;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS configuration pour toutes les URLs sauf les fichiers images et CSS
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Autoriser les requêtes provenant de votre application Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autoriser les méthodes HTTP nécessaires
                .allowedHeaders("*") // Autoriser tous les en-têtes
                .allowCredentials(true); // Autoriser les cookies et les en-têtes d'autorisation

        // Exclude CSS and image files from CORS configuration
        registry.addMapping("/css/**") // Assuming CSS files are in a "css" directory
                .allowedMethods("GET"); // Allow only GET requests for CSS files

        registry.addMapping("/images/**") // Assuming image files are in an "images" directory
                .allowedMethods("GET"); // Allow only GET requests for image files
    }
}