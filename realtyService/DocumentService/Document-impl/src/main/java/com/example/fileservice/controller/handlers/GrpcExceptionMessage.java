package com.example.fileservice.controller.handlers;

import io.grpc.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrpcExceptionMessage {
    private String message;


    private Status code;

    private String exceptionName;
}
