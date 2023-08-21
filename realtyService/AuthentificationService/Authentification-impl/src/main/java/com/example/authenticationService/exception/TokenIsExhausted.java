package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class TokenIsExhausted extends ServiceSecurityException{
    public TokenIsExhausted() {
        super("Session time has run out", HttpStatus.NOT_ACCEPTABLE);
    }
}
