package com.metaverse.station.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
//                .allowedOrigins("https://www.modumeta.com")
//                .allowedOrigins("https://cdn.metaversestation.shop")
//                .allowedOrigins("https://api.metaversestation.shop")
//                .allowedOrigins("https://api.gather.town")
//                .allowedOrigins("http://127.0.0.1:8080")
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowCredentials(true)
                .maxAge(1500);

    }
}
