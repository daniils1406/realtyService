package com.example.advertismentService.exception.builder;

import com.example.advertismentService.exception.EntityNotFoundException;

import java.util.UUID;

public class BuilderNotFoundException extends EntityNotFoundException {
    public BuilderNotFoundException(UUID id){
        super(String.format("Builder with id=%s not found",id));
    }
    public BuilderNotFoundException(String name){
        super(String.format("Builder with name=%s not found",name));
    }
}
