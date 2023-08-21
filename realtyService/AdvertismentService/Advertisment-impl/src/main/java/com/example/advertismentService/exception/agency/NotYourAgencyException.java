package com.example.advertismentService.exception.agency;

import com.example.advertismentService.exception.NotYourException;
import org.springframework.http.HttpStatus;

public class NotYourAgencyException extends NotYourException {
    public NotYourAgencyException() {
        super("You trying change not your agency", HttpStatus.BAD_REQUEST);
    }
}
