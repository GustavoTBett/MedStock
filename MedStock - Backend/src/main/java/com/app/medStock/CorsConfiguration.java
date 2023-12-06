package com.app.medStock;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Mapeie para seus endpoints específicos
                .allowedOrigins("http://localhost:3000") // Permita apenas essa origem
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowCredentials(true) // Permitir credenciais (se necessário)
                .maxAge(3600); // Tempo de cache da configuração CORS
    }
}