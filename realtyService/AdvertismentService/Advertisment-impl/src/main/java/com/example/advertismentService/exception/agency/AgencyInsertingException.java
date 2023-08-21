package com.example.advertismentService.exception.agency;

import com.example.advertismentService.exception.EntityInsertingException;

public class AgencyInsertingException extends EntityInsertingException {
    public AgencyInsertingException(){
        super("Agency insert exception");
    }
}
