package com.example.advertismentService.exception.owner;

import com.example.advertismentService.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class ThisOwnerNotHaveRealty extends ServiceException {
    public ThisOwnerNotHaveRealty(String id) {
        super(String.format("Owner with id %s not have any realty",id), HttpStatus.NOT_FOUND);
    }
}
