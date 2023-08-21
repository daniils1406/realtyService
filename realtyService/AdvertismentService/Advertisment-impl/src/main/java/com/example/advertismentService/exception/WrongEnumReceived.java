package com.example.advertismentService.exception;

import org.springframework.http.HttpStatus;

public class WrongEnumReceived extends ServiceException{

    public WrongEnumReceived() {
        super("Incorrect ENUM received", HttpStatus.BAD_REQUEST);
    }
}
