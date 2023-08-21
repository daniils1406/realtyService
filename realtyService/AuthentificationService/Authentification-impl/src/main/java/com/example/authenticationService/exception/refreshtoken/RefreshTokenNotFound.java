package com.example.authenticationService.exception.refreshtoken;

import com.example.authenticationService.exception.EntityNotFoundException;

import java.util.UUID;

public class RefreshTokenNotFound extends EntityNotFoundException {
    public RefreshTokenNotFound(UUID id,String identifier) {
        super(String.format("Refresh token with %s %s not found",id,identifier));
    }
}