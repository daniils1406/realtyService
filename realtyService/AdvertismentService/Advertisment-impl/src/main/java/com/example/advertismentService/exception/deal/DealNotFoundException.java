package com.example.advertismentService.exception.deal;

import com.example.advertismentService.exception.EntityNotFoundException;

import java.util.UUID;

public class DealNotFoundException extends EntityNotFoundException {
    public DealNotFoundException(UUID id){
        super(String.format("Deal with id %s not found",id));
    }
}
