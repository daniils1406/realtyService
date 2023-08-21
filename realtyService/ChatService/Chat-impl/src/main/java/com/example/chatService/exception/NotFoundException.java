package com.example.chatService.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ServiceException {
    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
