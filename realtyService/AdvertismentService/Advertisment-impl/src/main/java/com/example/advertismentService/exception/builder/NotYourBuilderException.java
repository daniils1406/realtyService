package com.example.advertismentService.exception.builder;

import com.example.advertismentService.exception.NotYourException;
import org.springframework.http.HttpStatus;

public class NotYourBuilderException extends NotYourException {
    public NotYourBuilderException() {
        super("You trying to change builder that not your!", HttpStatus.BAD_REQUEST);
    }
}
