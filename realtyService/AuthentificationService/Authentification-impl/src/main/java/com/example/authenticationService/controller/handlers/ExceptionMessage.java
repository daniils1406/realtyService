package com.example.authenticationService.controller.handlers;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Сообщение описывающее возникшую исключительную ситуацию.
 */
@Data
@Builder
public class ExceptionMessage {

    /**
     * Сообщение исключения
     */
    private String message;


    private HttpStatus code;
    /**
     * Наименование исключения
     */
    private String exceptionName;
}
