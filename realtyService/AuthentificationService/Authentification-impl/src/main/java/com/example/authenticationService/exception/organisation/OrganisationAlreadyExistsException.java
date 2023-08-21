package com.example.authenticationService.exception.organisation;

import com.example.authenticationService.exception.EntityAlreadyExistsException;

public class OrganisationAlreadyExistsException extends EntityAlreadyExistsException {
    public OrganisationAlreadyExistsException(String login) {
        super(String.format("Organisation representative with login %s already exists",login));
    }
}
