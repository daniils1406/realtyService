package com.example.authenticationService.security.utils;

import com.example.authenticationService.security.entity.ParsedToken;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JwtUtil {
    Map<String, String> generateTokens(String id,String subject, String authority, String issuer);

    Authentication buildAuthentication(String token) throws JWTVerificationException;

    ParsedToken parse(String token) throws JWTVerificationException;
}
