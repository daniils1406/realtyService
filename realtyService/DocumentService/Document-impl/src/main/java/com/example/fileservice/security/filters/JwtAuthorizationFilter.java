package com.example.fileservice.security.filters;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fileservice.redis.JwtBlackListService;
import com.example.fileservice.security.utils.AuthorizationHeaderUtil;
import com.example.fileservice.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.fileservice.security.utils.Constants.*;


@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final JwtUtil jwtUtil;

    private final JwtBlackListService jwtBlackListService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(CHECK_FILES) ||
                request.getServletPath().equals(DOWNLOAD_FILES)) {
            filterChain.doFilter(request, response);
        } else {
            if (authorizationHeaderUtil.hasToken(request)) {
                String jwt = authorizationHeaderUtil.getToken(request);

                if (jwtBlackListService.exist(jwt)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }

                try {
                    Authentication authentication = jwtUtil.buildAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } catch (JWTVerificationException e) {
                    logger.info(e.getMessage());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
