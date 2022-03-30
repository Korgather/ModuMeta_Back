package com.metaverse.station.back.config;

import com.metaverse.station.back.oauth.token.AuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
//    @Value("${jwt.secret}")
    private String secret = "57293cee329856f41a25abb84ffa2d8f5ebb834a";


    @Bean
    public AuthTokenProvider jwtProvider() {
        System.out.println("key = " + secret);
        return new AuthTokenProvider(secret);
    }
}
