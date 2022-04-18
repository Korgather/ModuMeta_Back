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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


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
                .authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .antMatchers(HttpMethod.POST,"/").permitAll()
                    .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                //게시글 작성
                    .antMatchers(HttpMethod.POST,"/api/v1/posts/**").hasAnyAuthority(RoleType.USER.getCode())
                //게시글 수정
                    .antMatchers(HttpMethod.PUT,"/api/v1/posts/*").hasAnyAuthority(RoleType.USER.getCode())
                //게시글 삭제
                    .antMatchers(HttpMethod.DELETE,"/api/v1/posts/*").hasAnyAuthority(RoleType.USER.getCode())
                //댓글 수정
                    .antMatchers(HttpMethod.PUT,"/api/v1/comments/*").hasAnyAuthority(RoleType.USER.getCode())
                //댓글 삭제
                    .antMatchers(HttpMethod.DELETE,"/api/v1/comments/*").hasAnyAuthority(RoleType.USER.getCode())
                //대댓글 작성
                    .antMatchers(HttpMethod.POST,"/api/v1/comments/*").hasAnyAuthority(RoleType.USER.getCode())
                //대댓글 수정
                    .antMatchers(HttpMethod.PUT,"/api/v1/replies/*").hasAnyAuthority(RoleType.USER.getCode())
                //대댓글 삭제
                    .antMatchers(HttpMethod.DELETE,"/api/v1/replies/*").hasAnyAuthority(RoleType.USER.getCode())
                //이미지 업로드요청
                    .antMatchers(HttpMethod.POST,"/api/v1/upload").hasAnyAuthority(RoleType.USER.getCode())
                //이미지 삭제
                    .antMatchers(HttpMethod.DELETE,"/api/v1/upload").hasAnyAuthority(RoleType.USER.getCode())
                //좋아요
                    .antMatchers(HttpMethod.POST,"/api/v1/like/**").hasAnyAuthority(RoleType.USER.getCode())
                //유저정보조회
                    .antMatchers(HttpMethod.GET,"/api/v1/users").hasAnyAuthority(RoleType.USER.getCode())
                //Refresh토큰
                    .antMatchers(HttpMethod.GET,"/api/v1/auth/refresh").hasAnyAuthority(RoleType.USER.getCode())
                //유저정보 수정 관련
                    .antMatchers(HttpMethod.PUT,"/api/v1/users/**").hasAnyAuthority(RoleType.USER.getCode())
                //유저 프로필이미지 업로드
                    .antMatchers(HttpMethod.POST,"/api/v1/users/profileimage").hasAnyAuthority(RoleType.USER.getCode())
                //단일 게시글 조회
                    .antMatchers(HttpMethod.GET,"/api/v1/posts/**").permitAll()
                //전체 게시글 조회
                    .antMatchers(HttpMethod.GET,"/api/v1/posts").permitAll()
                //알림 삭제
                    .antMatchers(HttpMethod.DELETE, "/api/v1/notification/**").hasAnyAuthority(RoleType.USER.getCode())
                //피드백 포스트
                    .antMatchers(HttpMethod.POST, "/api/v1/feedback").hasAnyAuthority(RoleType.USER.getCode())
                //게더타운 API
                    .antMatchers(HttpMethod.POST,"/api/v1/gathertown/**").hasAnyAuthority(RoleType.USER.getCode())
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
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
     * auth 매니저 설정
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * security 설정 시, 사용할 인코더 설정
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 토큰 필터 설정
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * 쿠키 기반 인가 Repository
     * 인가 응답을 연계 하고 검증할 때 사용.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth 인증 성공 핸들러
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
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

//    /*
//     * Cors 설정
//     * */
//    @Bean
//    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
//        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
//        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
//        corsConfig.setAllowCredentials(true);
//        corsConfig.setMaxAge(corsConfig.getMaxAge());
//
//        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
//        return corsConfigSource;
//    }
}
