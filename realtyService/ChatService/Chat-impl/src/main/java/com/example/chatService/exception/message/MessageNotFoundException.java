package com.example.chatService.exception.message;

import com.example.chatService.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public class MessageNotFoundException extends NotFoundException {
    public MessageNotFoundException() {
        super("Message with such id not found", HttpStatus.BAD_REQUEST);
    }
}
