package com.example.authenticationService.security.filters;

import com.example.authenticationService.security.utils.AuthorizationHeaderUtil;
import com.example.authenticationService.security.utils.JwtUtil;
import com.example.authenticationService.redis.JwtBlackListService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.authenticationService.security.utils.Constants;
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


@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final JwtUtil jwtUtil;

    private final JwtBlackListService jwtBlackListService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(Constants.CIAN_USER_LOG_IN_URL) ||
                request.getServletPath().equals(Constants.AGENT_LOG_IN_URL) ||
                request.getServletPath().equals(Constants.ORGANISATION_LOG_IN_URL) ||
                request.getServletPath().contains(Constants.CIAN_USER_GET_ALL) ||
                request.getServletPath().contains(Constants.AGENT_GET_ALL) ||
                request.getServletPath().contains(Constants.ORGANISATION_GET_ALL) ||
                request.getServletPath().equals(Constants.USER_GET_BY_ID) ||
                request.getServletPath().equals(Constants.AGENT_GET_BY_ID) ||
                request.getServletPath().equals(Constants.ORGANISATION_GET_BY_ID)) {
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
