package com.example.advertismentService.controllers.handlers;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionMessage {

    private String message;


    private HttpStatus code;

    private String exceptionName;
}
