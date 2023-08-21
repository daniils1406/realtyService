package com.example.advertismentService.security.utils;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.advertismentService.security.entity.ParsedToken;
import org.springframework.security.core.Authentication;

public interface JwtUtil {

    Authentication buildAuthentication(String token) throws JWTVerificationException;

    ParsedToken parse(String token) throws JWTVerificationException;
}
