package com.example.chatService.exception.room;

import com.example.chatService.exception.NotOwnerException;
import com.example.chatService.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class NotYourException extends ServiceException {
    public NotYourException() {
        super("You trying to get information that not yours", HttpStatus.BAD_REQUEST);
    }
}
