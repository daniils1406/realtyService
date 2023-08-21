package com.example.authenticationService.controller.handlers;

import com.example.authenticationService.exception.agent.ThisAgencyNotExist;
import com.example.authenticationService.exception.cianuser.DocumentsOfUserNotVerified;
import com.example.authenticationService.exception.organisation.ThisOrganisationNotExist;
import com.example.authenticationService.exception.*;
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
    public final ResponseEntity<ExceptionMessage> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public final ResponseEntity<ExceptionMessage> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ServiceSecurityException.class)
    public final ResponseEntity<ExceptionMessage> handleServiceSecurityException(ServiceSecurityException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(WrongUsertypeEnumException.class)
    public final ResponseEntity<ExceptionMessage> handleWrongUsertypeEnumException(WrongUsertypeEnumException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(DocumentsOfUserNotVerified.class)
    public final ResponseEntity<ExceptionMessage> handleDocumentNoVerifiedException(DocumentsOfUserNotVerified ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
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

    @ExceptionHandler(ChangeForeignAccount.class)
    public final ResponseEntity<ExceptionMessage> handleWrongUsertypeEnumException(ChangeForeignAccount ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public final ResponseEntity<ExceptionMessage> handleWrongUsertypeEnumException(TokenExpiredException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ThisAgencyNotExist.class)
    public final ResponseEntity<ExceptionMessage> handleDocumentNoVerifiedException(ThisAgencyNotExist ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ThisOrganisationNotExist.class)
    public final ResponseEntity<ExceptionMessage> handleDocumentNoVerifiedException(ThisOrganisationNotExist ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }


}