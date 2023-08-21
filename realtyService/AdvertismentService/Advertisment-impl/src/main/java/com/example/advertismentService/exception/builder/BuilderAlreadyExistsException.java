package com.example.advertismentService.exception.builder;

import com.example.advertismentService.exception.EntityAlreadyExistsException;

public class BuilderAlreadyExistsException extends EntityAlreadyExistsException {
    public BuilderAlreadyExistsException(String name){
        super(String.format("Builder with name %s already exists",name));
    }
}
