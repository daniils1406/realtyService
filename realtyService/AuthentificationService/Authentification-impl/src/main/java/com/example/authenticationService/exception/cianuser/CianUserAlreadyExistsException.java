package com.example.authenticationService.exception.cianuser;

import com.example.authenticationService.exception.EntityAlreadyExistsException;

public class CianUserAlreadyExistsException extends EntityAlreadyExistsException {
    public CianUserAlreadyExistsException(String login) {
        super(String.format("Cian user with login %s already exists",login));
    }
}
