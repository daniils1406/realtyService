package com.example.authenticationService.exception.verificationtoken;

import com.example.authenticationService.exception.EntityNotFoundException;

import java.util.UUID;

public class VerificationTokenNotFoundException extends EntityNotFoundException {
    public VerificationTokenNotFoundException(UUID id) {
        super(String.format("Verification token with id %s not found",id));
    }
}
