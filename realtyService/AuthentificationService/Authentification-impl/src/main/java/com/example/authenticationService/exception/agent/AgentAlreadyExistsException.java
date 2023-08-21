package com.example.authenticationService.exception.agent;

import com.example.authenticationService.exception.EntityAlreadyExistsException;

public class AgentAlreadyExistsException extends EntityAlreadyExistsException {
    public AgentAlreadyExistsException(String login) {
        super(String.format("Organisation representative with login %s already exists", login));
    }
}
