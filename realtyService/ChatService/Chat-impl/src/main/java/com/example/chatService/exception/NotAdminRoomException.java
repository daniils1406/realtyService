package com.example.chatService.exception;

import org.springframework.http.HttpStatus;

public class NotAdminRoomException extends ServiceException{
    public NotAdminRoomException() {
        super("You can`t delete member of room!", HttpStatus.BAD_REQUEST);
    }
}
