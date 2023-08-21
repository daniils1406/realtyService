package com.example.chatService.exception;

import org.springframework.http.HttpStatus;

public class NotOwnerException extends ServiceException{
    public NotOwnerException() {
        super(String.format("You try to change message, that not yours!"), HttpStatus.BAD_REQUEST);
    }
}
