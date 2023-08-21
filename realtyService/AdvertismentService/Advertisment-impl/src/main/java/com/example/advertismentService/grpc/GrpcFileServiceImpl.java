package com.example.advertismentService.grpc;


import document.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GrpcFileServiceImpl {
    private final DiscoveryClient discoveryClient;


    public boolean checkForTheNecessaryFiles(UUID id) {
        String documentServiceHost = discoveryClient.getInstances("documentService").stream().findFirst().map(s -> s.getHost()).get();

        int documentServiceGrpcPort = Integer.parseInt(discoveryClient.getInstances("documentService").stream().findFirst().map(s -> s.getMetadata().get("gRPC_port")).get());

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(documentServiceHost, documentServiceGrpcPort)
                .usePlaintext()
                .build();
        FileServiceGrpc.FileServiceBlockingStub client = FileServiceGrpc.newBlockingStub(channel);
        CheckResponse checkResponse = client.checkForTheNecessaryFiles(CheckRequest.newBuilder()
                .setId(id.toString())
                .build());
        boolean result = checkResponse.getIsVerify();
        channel.shutdownNow();
        return result;
    }

    public void setNewEntityTypeInDocumentService(UUID id, String newType) {
        String documentServiceHost = discoveryClient.getInstances("documentService").stream().findFirst().map(s -> s.getHost()).get();

        int documentServiceGrpcPort = Integer.parseInt(discoveryClient.getInstances("documentService").stream().findFirst().map(s -> s.getMetadata().get("gRPC_port")).get());

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(documentServiceHost, documentServiceGrpcPort)
                .usePlaintext()
                .build();
        FileServiceGrpc.FileServiceBlockingStub client = FileServiceGrpc.newBlockingStub(channel);
        client.setNewTypeOfEntity(NewEntityType.newBuilder()
                .setType(EntityType.valueOf(newType))
                .setId(id.toString())
                .build());
        channel.shutdownNow();

    }
}