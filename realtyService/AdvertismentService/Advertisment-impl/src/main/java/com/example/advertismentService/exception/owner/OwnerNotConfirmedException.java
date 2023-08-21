package com.example.advertismentService.exception.owner;

import com.example.advertismentService.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class OwnerNotConfirmedException extends ServiceException {
    public OwnerNotConfirmedException(String id) {
        super(String.format("User with id %s have not enough document",id), HttpStatus.BAD_REQUEST);
    }

    public OwnerNotConfirmedException() {
        super("You need to upload document first", HttpStatus.BAD_REQUEST);
    }
}
