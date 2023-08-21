package com.example.authenticationService.exception.agent;

import com.example.authenticationService.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class AgentAlreadyWork extends ServiceException {
    public AgentAlreadyWork() {
        super(String.format("This agent already work in other company"), HttpStatus.BAD_REQUEST);
    }
}
