package com.example.chatService.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException{

    HttpStatus httpStatus;

    public ServiceException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus=httpStatus;
    }
}
