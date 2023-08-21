package com.example.chatService.security.utils;

import com.example.chatService.security.entity.ParsedToken;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.Authentication;

public interface JwtUtil {

    Authentication buildAuthentication(String token) throws JWTVerificationException;

    ParsedToken parse(String token) throws JWTVerificationException;
}
