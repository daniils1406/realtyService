package com.example.chatService.exception;

import org.springframework.http.HttpStatus;

public class InvalidRoomNameException extends ServiceException{
    public InvalidRoomNameException() {
        super(String.format("You can`t create chat with such name"), HttpStatus.BAD_REQUEST);
    }
}
