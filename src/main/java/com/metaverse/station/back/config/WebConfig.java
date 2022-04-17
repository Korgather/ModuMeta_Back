package com.metaverse.station.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*")
                .allowedOrigins("https://www.modumeta.com")
//                .allowedOrigins("https://cdn.metabusstation.shop")
//                .allowedOrigins("https://api.metabusstation.shop")
//                .allowedOrigins("https://api.gather.town")
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(1500);

    }
}
