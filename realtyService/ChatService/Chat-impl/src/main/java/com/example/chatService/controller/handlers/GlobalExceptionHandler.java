package com.example.chatService.controller.handlers;

import com.example.chatService.exception.NotAdminRoomException;
import com.example.chatService.exception.NotFoundException;
import com.example.chatService.exception.NotOwnerException;
import com.example.chatService.exception.room.ImmutableRoomException;
import com.example.chatService.exception.InvalidRoomNameException;
import com.example.chatService.exception.room.NotYourException;
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

    @ExceptionHandler(StatusRuntimeException.class)
    public final ResponseEntity<GrpcExceptionMessage> handleAlreadyExistsExceptio(StatusRuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(GrpcExceptionMessage.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .code(ex.getStatus())
                        .build());
    }

    @ExceptionHandler(InvalidRoomNameException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(InvalidRoomNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(ImmutableRoomException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(ImmutableRoomException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(NotAdminRoomException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(NotAdminRoomException ex) {
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

    @ExceptionHandler(NotOwnerException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(NotOwnerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(NotYourException.class)
    public final ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(NotYourException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }
}