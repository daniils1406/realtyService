package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class ServiceSecurityException extends ServiceException{
    public ServiceSecurityException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
