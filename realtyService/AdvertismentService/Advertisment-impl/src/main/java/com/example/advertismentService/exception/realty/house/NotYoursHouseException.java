package com.example.advertismentService.exception.realty.house;

import com.example.advertismentService.exception.NotYourException;
import org.springframework.http.HttpStatus;

public class NotYoursHouseException extends NotYourException {
    public NotYoursHouseException() {
        super("You trying to change not your house!", HttpStatus.BAD_REQUEST);
    }
}
