package com.example.authenticationService.exception.organisation;

import com.example.authenticationService.exception.EntityNotFoundException;

public class ThisOrganisationNotExist extends EntityNotFoundException {
    public ThisOrganisationNotExist() {
        super("Organisation with such id not found!");
    }
}
