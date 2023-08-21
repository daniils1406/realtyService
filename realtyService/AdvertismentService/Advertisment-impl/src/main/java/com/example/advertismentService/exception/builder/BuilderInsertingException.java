package com.example.advertismentService.exception.builder;

import com.example.advertismentService.exception.EntityInsertingException;

public class BuilderInsertingException extends EntityInsertingException {
    public BuilderInsertingException(){
        super("Builder insert exception");
    }
}
