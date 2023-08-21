package com.example.advertismentService.exception.agency;

import com.example.advertismentService.exception.EntityNotFoundException;

import java.util.UUID;

public class AgencyNotFoundException extends EntityNotFoundException {
    public AgencyNotFoundException(UUID id){
        super(String.format("Agency with id %s not found",id));
    }
}
