package com.example.authenticationService.exception.organisation;

import com.example.authenticationService.exception.EntityNotFoundException;

import java.util.UUID;

public class OrganisationNotFoundException extends EntityNotFoundException {
    public OrganisationNotFoundException(String message) {
        super(String.format("Organisation representative with login %s not found",message));
    }

    public OrganisationNotFoundException(UUID id) {
        super(String.format("Organisation representative with id %s not found",id));
    }
}
