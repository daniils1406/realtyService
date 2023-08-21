package com.example.fileservice.security.utils;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fileservice.security.entity.ParsedToken;
import org.springframework.security.core.Authentication;

public interface JwtUtil {

    Authentication buildAuthentication(String token) throws JWTVerificationException;

    ParsedToken parse(String token) throws JWTVerificationException;
}
