package com.example.advertismentService.exception.realty.house;

import com.example.advertismentService.exception.EntityNotFoundException;

import java.util.UUID;

public class HouseNotFoundException extends EntityNotFoundException {
    public HouseNotFoundException(UUID id){
        super(String.format("House with id %s not found",id));
    }
}
