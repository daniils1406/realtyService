package com.example.chatService.exception.room;

import com.example.chatService.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends NotFoundException {
    public RoomNotFoundException() {
        super("Message with such id not found", HttpStatus.BAD_REQUEST);
    }
}
