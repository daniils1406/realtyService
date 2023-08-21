package com.example.fileservice.controller.handlers;

import com.example.fileservice.exception.ServiceException;
import dto.response.ResponseErrorMessage;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ExceptionMessage> handleServiceException(ServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .code(HttpStatus.NOT_FOUND)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseErrorMessage.builder()
                        .errors(ex.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> new ResponseErrorMessage.Error(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage()))
                                .collect(Collectors.toList()))
                        .build());
    }


    @ExceptionHandler(StatusRuntimeException.class)
    public final ResponseEntity<GrpcExceptionMessage> handleAlreadyExistsExceptio(StatusRuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(GrpcExceptionMessage.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .code(ex.getStatus())
                        .build());
    }
}