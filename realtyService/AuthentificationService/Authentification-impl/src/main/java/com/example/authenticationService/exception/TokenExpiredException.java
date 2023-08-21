package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends ServiceSecurityException{
    public TokenExpiredException() {
        super("Token has expired", HttpStatus.UNAUTHORIZED);
    }
}
