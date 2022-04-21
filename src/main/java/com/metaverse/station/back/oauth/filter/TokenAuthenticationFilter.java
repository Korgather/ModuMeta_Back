package com.metaverse.station.back.oauth.filter;

import com.metaverse.station.back.oauth.token.AuthToken;
import com.metaverse.station.back.oauth.token.AuthTokenProvider;
import com.metaverse.station.back.utils.HeaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthTokenProvider tokenProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String path = request.getServletPath();
        String method = request.getMethod();
//        return !path.startsWith("/api");
        return isResourceUrl(path,method);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws ServletException, IOException {

        String tokenStr = HeaderUtil.getAccessToken(request);

        log.info(request.getRequestURI());

        AuthToken token = tokenProvider.convertAuthToken(tokenStr);

        if (token.validate()) {

            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private boolean isResourceUrl(String url, String method) {
        boolean isResourceUrl = false;

        switch (method.toUpperCase()){
            case "GET":
                if(!url.startsWith("/api")){
                    return true;
                }
                else{
                    List<String> resourceRequests = Arrays.asList(
                            "/api/v1/posts");
                    for (String resourceRequest : resourceRequests) {
                        if (url.contains(resourceRequest)) {
                            isResourceUrl = true;
                        }
                    }
                }
                break;

        }
        return isResourceUrl;
    }

}
