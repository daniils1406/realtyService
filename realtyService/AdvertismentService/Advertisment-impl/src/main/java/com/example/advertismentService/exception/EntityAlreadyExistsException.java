package com.example.advertismentService.exception;

import org.springframework.http.HttpStatus;


public class EntityAlreadyExistsException extends ServiceException{
    public EntityAlreadyExistsException(String message){
        super(message,HttpStatus.CONFLICT);
    }
}
