package com.metaverse.station.back.config;

import com.metaverse.station.back.config.properties.AppProperties;
import com.metaverse.station.back.config.properties.CorsProperties;
import com.metaverse.station.back.domain.user.UserRefreshTokenRepository;
import com.metaverse.station.back.oauth.domain.RoleType;
import com.metaverse.station.back.oauth.exception.RestAuthenticationEntryPoint;
import com.metaverse.station.back.oauth.filter.TokenAuthenticationFilter;
import com.metaverse.station.back.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.metaverse.station.back.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.metaverse.station.back.oauth.handler.TokenAccessDeniedHandler;
import com.metaverse.station.back.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.metaverse.station.back.oauth.service.CustomOAuth2UserService;
import com.metaverse.station.back.oauth.service.CustomUserDetailsService;
import com.metaverse.station.back.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .cors()
                    .and()
                .headers().frameOptions().disable()
                    .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(tokenAccessDeniedHandler)
                    .and()
//                .mvcMatcher("/api/v1/**")
                .authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //????????? ??????
                    .mvcMatchers(HttpMethod.POST,"/api/v1/posts/**").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ??????
                    .mvcMatchers(HttpMethod.PUT,"/api/v1/posts/*").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ??????
                    .mvcMatchers(HttpMethod.DELETE,"/api/v1/posts/*").hasAnyAuthority(RoleType.USER.getCode())
                //?????? ??????
                    .mvcMatchers(HttpMethod.PUT,"/api/v1/comments/*").hasAnyAuthority(RoleType.USER.getCode())
                //?????? ??????
                    .mvcMatchers(HttpMethod.DELETE,"/api/v1/comments/*").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ??????
                    .mvcMatchers(HttpMethod.POST,"/api/v1/comments/*").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ??????
                    .mvcMatchers(HttpMethod.PUT,"/api/v1/replies/*").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ??????
                    .mvcMatchers(HttpMethod.DELETE,"/api/v1/replies/*").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ???????????????
                    .mvcMatchers(HttpMethod.POST,"/api/v1/upload").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ??????
                    .mvcMatchers(HttpMethod.DELETE,"/api/v1/upload").hasAnyAuthority(RoleType.USER.getCode())
                //?????????
                    .mvcMatchers(HttpMethod.POST,"/api/v1/like/**").hasAnyAuthority(RoleType.USER.getCode())
                //??????????????????
                    .mvcMatchers(HttpMethod.GET,"/api/v1/users").hasAnyAuthority(RoleType.USER.getCode())
                //Refresh??????
                    .mvcMatchers(HttpMethod.GET,"/api/v1/auth/refresh").hasAnyAuthority(RoleType.USER.getCode())
                //???????????? ?????? ??????
                    .mvcMatchers(HttpMethod.PUT,"/api/v1/users/**").hasAnyAuthority(RoleType.USER.getCode())
                //?????? ?????????????????? ?????????
                    .mvcMatchers(HttpMethod.POST,"/api/v1/users/profileimage").hasAnyAuthority(RoleType.USER.getCode())
                //?????? ????????? ??????
                    .mvcMatchers(HttpMethod.GET,"/api/v1/posts/**").permitAll()
                //?????? ????????? ??????
                    .mvcMatchers(HttpMethod.GET,"/api/v1/posts").permitAll()
                //?????? ??????
                    .mvcMatchers(HttpMethod.DELETE, "/api/v1/notification/**").hasAnyAuthority(RoleType.USER.getCode())
                //????????? ?????????
                    .mvcMatchers(HttpMethod.POST, "/api/v1/feedback").hasAnyAuthority(RoleType.USER.getCode())
                //???????????? API
                    .mvcMatchers(HttpMethod.POST,"/api/v1/gathertown/**").hasAnyAuthority(RoleType.USER.getCode())
                    .mvcMatchers("/h2-console/**").permitAll()
                    .mvcMatchers("/api/v1/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
                    .anyRequest().denyAll()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                    .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                    .and()
                .redirectionEndpoint()
                    .baseUri("/*/oauth2/code/*")
                    .and()
                .userInfoEndpoint()
                    .userService(oAuth2UserService)
                    .and()
                .successHandler(oAuth2AuthenticationSuccessHandler())
                .failureHandler(oAuth2AuthenticationFailureHandler());

        httpSecurity.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    /*
     * auth ????????? ??????
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * security ?????? ???, ????????? ????????? ??????
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * ?????? ?????? ??????
     * */
//    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * ?????? ?????? ?????? Repository
     * ?????? ????????? ?????? ?????? ????????? ??? ??????.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

}
