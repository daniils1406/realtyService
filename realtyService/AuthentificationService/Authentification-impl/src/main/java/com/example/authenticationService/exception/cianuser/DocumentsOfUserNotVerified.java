package com.example.authenticationService.exception.cianuser;

import com.example.authenticationService.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class DocumentsOfUserNotVerified extends ServiceException {
    public DocumentsOfUserNotVerified(String id) {
        super(String.format("Documents of user with id %s not confirmed",id), HttpStatus.BAD_REQUEST);
    }
}