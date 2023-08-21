package com.example.authenticationService.exception.cianuser;

import com.example.authenticationService.exception.EntityNotFoundException;

import java.util.UUID;

public class CianUserNotFoundException extends EntityNotFoundException {
    public CianUserNotFoundException(UUID id) {
        super(String.format("User with id %s not found",id));
    }

    public CianUserNotFoundException(String login) {
        super(String.format("User with login %s not found",login));
    }
}
