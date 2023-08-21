package com.example.advertismentService.exception.realty.flat;

import com.example.advertismentService.exception.NotYourException;
import org.springframework.http.HttpStatus;

public class NotYourFlatException extends NotYourException {
    public NotYourFlatException() {
        super("Your trying change flat which not yours!", HttpStatus.BAD_REQUEST);
    }
}
