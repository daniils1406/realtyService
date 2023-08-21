package com.example.advertismentService.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ServiceException{
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
