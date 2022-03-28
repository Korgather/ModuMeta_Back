package com.metaverse.station.back.config.auth;

import com.metaverse.station.back.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests().antMatchers("/","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.GUEST.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

//        httpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
//                .and()
//                .headers().frameOptions().disable();
    }
}
