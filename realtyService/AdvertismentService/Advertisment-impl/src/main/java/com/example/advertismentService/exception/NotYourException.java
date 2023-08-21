package com.example.advertismentService.exception;

import org.springframework.http.HttpStatus;

public class NotYourException extends ServiceException{
    public NotYourException(String message, HttpStatus status) {
        super(message, status);
    }
}
