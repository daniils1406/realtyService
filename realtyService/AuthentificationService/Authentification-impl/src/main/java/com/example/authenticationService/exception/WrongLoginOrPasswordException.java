package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class WrongLoginOrPasswordException extends ServiceSecurityException{
    public WrongLoginOrPasswordException() {
        super("Wrong login or password", HttpStatus.NOT_ACCEPTABLE);
    }
}
