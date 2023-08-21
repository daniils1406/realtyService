package com.example.advertismentService.exception.residentialcomplex;

import com.example.advertismentService.exception.EntityAlreadyExistsException;

import java.util.UUID;

public class ResidentialComplexAlreadyExistsException extends EntityAlreadyExistsException {
    public ResidentialComplexAlreadyExistsException(String name){
        super(String.format("Residential complex with name %s already exists",name));
    }
    public ResidentialComplexAlreadyExistsException(UUID id){
        super(String.format("Residential complex with id %s already exists",id));
    }
}
