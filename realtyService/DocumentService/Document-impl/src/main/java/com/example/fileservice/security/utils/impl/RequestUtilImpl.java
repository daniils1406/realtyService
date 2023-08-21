package com.example.fileservice.security.utils.impl;


import com.example.fileservice.security.utils.AuthorizationHeaderUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 24.06.2022
 * 29. Spring Boot REST + Security
 *
 * @author Sidikov Marsel (Akvelon)
 * @version v1.0
 */
@Component
public class RequestUtilImpl implements AuthorizationHeaderUtil {

    private static final String AUTHORIZATION_HEADER_NAME = "AUTHORIZATION";

    private static final String OLD_AUTHORIZATION_HEADER_NAME = "Access-Token";

    private static final String BEARER = "Bearer ";

    @Override
    public boolean hasToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public boolean hasAuthorizationToken(HttpServletRequest request) {
        String header = request.getHeader(OLD_AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return authorizationHeader.substring(BEARER.length());
    }

    @Override
    public String getOldToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(OLD_AUTHORIZATION_HEADER_NAME);
        return authorizationHeader.substring(BEARER.length());
    }
}