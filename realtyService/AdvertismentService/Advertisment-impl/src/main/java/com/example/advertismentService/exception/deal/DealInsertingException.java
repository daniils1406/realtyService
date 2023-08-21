package com.example.advertismentService.exception.deal;

import com.example.advertismentService.exception.EntityInsertingException;

public class DealInsertingException extends EntityInsertingException {
    public DealInsertingException(){
        super("Deal insert exception");
    }
}