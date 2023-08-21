package com.example.authenticationService.exception.agent;

import com.example.authenticationService.exception.EntityNotFoundException;

import java.util.UUID;

public class AgentNotFoundException extends EntityNotFoundException {
    public AgentNotFoundException(String message) {
        super(String.format("Agent with login %s not found",message));
    }

    public AgentNotFoundException(UUID id) {
        super(String.format("Agent with id %s not found",id));
    }
}