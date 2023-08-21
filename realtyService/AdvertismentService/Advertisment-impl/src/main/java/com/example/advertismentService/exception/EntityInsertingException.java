package com.example.advertismentService.exception;

import org.springframework.http.HttpStatus;

public class EntityInsertingException extends RuntimeException{
    HttpStatus httpStatus;
    public EntityInsertingException(String message){
        super(message);
        this.httpStatus=HttpStatus.BAD_REQUEST;
    }
}
