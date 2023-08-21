package com.example.chatService.exception.room;

import com.example.chatService.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class ImmutableRoomException extends ServiceException {
    public ImmutableRoomException() {
        super("This room can`t be change!", HttpStatus.BAD_REQUEST);
    }
}
