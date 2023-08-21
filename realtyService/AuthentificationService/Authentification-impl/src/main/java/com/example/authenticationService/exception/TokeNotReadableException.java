package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class TokeNotReadableException extends ServiceSecurityException{
    public TokeNotReadableException() {
        super("Wrong token", HttpStatus.UNAUTHORIZED);
    }
}
