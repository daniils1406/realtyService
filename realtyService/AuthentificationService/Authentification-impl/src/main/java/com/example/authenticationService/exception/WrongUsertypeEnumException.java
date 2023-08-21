package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class WrongUsertypeEnumException extends ServiceException{
    public WrongUsertypeEnumException() {
        super("Wrong usertype in repository", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
