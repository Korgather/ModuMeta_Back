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
                .allowedOrigins("https://www.modumeta.com","http://localhost:3000","https://zep.us")
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(1500);

    }
}
