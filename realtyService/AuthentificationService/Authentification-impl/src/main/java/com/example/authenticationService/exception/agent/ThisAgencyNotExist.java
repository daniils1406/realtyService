package com.example.authenticationService.exception.agent;

import com.example.authenticationService.exception.EntityNotFoundException;

public class ThisAgencyNotExist extends EntityNotFoundException {
    public ThisAgencyNotExist() {
        super("Agency wth such id not found!");
    }
}
