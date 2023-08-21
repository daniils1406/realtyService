package com.example.authenticationService.exception;

import org.springframework.http.HttpStatus;

public class ChangeForeignAccount extends ServiceException{
    public ChangeForeignAccount() {
        super("You trying change account that is not your!", HttpStatus.BAD_REQUEST);
    }
}
