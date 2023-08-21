package com.example.advertismentService.exception.agency;

import com.example.advertismentService.exception.EntityAlreadyExistsException;

public class AgencyAlreadyExistsException extends EntityAlreadyExistsException {
    public AgencyAlreadyExistsException(String name){
        super(String.format("Agency with name %s already exists",name));
    }
}
