package com.example.advertismentService.exception.realty.flat;

import com.example.advertismentService.exception.EntityNotFoundException;

import java.util.UUID;

public class FlatNotFoundException extends EntityNotFoundException {
    public FlatNotFoundException(UUID id){
        super(String.format("Flat with id %s not found",id));
    }
}
