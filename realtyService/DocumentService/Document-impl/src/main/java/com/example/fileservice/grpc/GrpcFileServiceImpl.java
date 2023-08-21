package com.example.fileservice.grpc;


import com.example.fileservice.exception.EntityNotFoundException;
import com.example.fileservice.service.FileService;
import com.google.protobuf.Any;
import com.google.rpc.ErrorInfo;
import document.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@GrpcService
@Component
@RequiredArgsConstructor
public class GrpcFileServiceImpl extends FileServiceGrpc.FileServiceImplBase {

    private final FileService filesService;

    @Override
    public void checkForTheNecessaryFiles(CheckRequest checkRequest, StreamObserver<CheckResponse> responseObserver) {
        UUID id = UUID.fromString(checkRequest.getId());
        boolean isVerify;
        try {
            isVerify = filesService.checkNecessaryFiles(id);
            CheckResponse checkResponse = CheckResponse.newBuilder()
                    .setIsVerify(isVerify)
                    .build();
            responseObserver.onNext(checkResponse);
            responseObserver.onCompleted();
        } catch (EntityNotFoundException ex) {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("Files of this user not found")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("File not found")
                            .putMetadata("id", id.toString())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void setNewTypeOfEntity(NewEntityType request, StreamObserver<Empty> responseObserver) {
        filesService.setNewEntityTypeById(UUID.fromString(request.getId()), request.getType().toString());
        Empty empty = Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }
}