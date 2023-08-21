package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ServiceSecurityException{
    public UnauthorizedException() {
        super("First you need to log in", HttpStatus.UNAUTHORIZED);
    }
}
