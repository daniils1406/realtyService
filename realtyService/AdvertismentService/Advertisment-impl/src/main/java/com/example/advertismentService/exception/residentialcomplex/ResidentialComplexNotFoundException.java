package com.example.advertismentService.exception.residentialcomplex;

import com.example.advertismentService.exception.EntityNotFoundException;

import java.util.UUID;

public class ResidentialComplexNotFoundException extends EntityNotFoundException {

    public ResidentialComplexNotFoundException(UUID id){
        super(String.format("Residential complex with id %s not found",id));
    }
}
