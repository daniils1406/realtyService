package com.example.advertismentService.controllers.handlers;

import com.example.advertismentService.exception.EntityAlreadyExistsException;
import com.example.advertismentService.exception.NotYourException;
import dto.response.ResponseErrorMessage;
import io.grpc.StatusRuntimeException;
import com.example.advertismentService.exception.EntityNotFoundException;
import com.example.advertismentService.exception.owner.OwnerNotConfirmedException;
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



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseErrorMessage.builder()
                        .errors(ex.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> new ResponseErrorMessage.Error(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage()))
                                .collect(Collectors.toList()))
                        .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionMessage> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionMessage.builder()
                        .code(ex.getHttpStatus())
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public final ResponseEntity<ExceptionMessage> handleAlreadyExistsException(EntityAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionMessage.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .code(ex.getHttpStatus())
                        .build());
    }


    @ExceptionHandler(OwnerNotConfirmedException.class)
    public final ResponseEntity<ExceptionMessage> handleAlreadyExistsExceptio(OwnerNotConfirmedException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionMessage.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .code(ex.getHttpStatus())
                        .build());
    }

    @ExceptionHandler(StatusRuntimeException.class)
    public final ResponseEntity<GrpcExceptionMessage> handleAlreadyExistsExceptio(StatusRuntimeException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(GrpcExceptionMessage.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .code(ex.getStatus())
                        .build());
    }


    @ExceptionHandler(NotYourException.class)
    public final ResponseEntity<ExceptionMessage> handleAlreadyExistsException(NotYourException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionMessage.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .code(ex.getHttpStatus())
                        .build());
    }
}