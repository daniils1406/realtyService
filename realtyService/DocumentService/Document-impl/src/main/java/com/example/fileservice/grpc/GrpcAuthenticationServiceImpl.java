package com.example.fileservice.grpc;

import advertisement.AdvertisementServiceGrpc;
import authentication.Authentication;
import authentication.CianUserServiceGrpc;
import authentication.IdRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class GrpcAuthenticationServiceImpl {
    private final DiscoveryClient discoveryClient;

    private String getHostOfService() {
        return discoveryClient.getInstances("authenticationService").stream().findFirst().map(s -> s.getHost()).get();
    }

    private Integer getPortOfService() {
        return Integer.parseInt(discoveryClient.getInstances("authenticationService").stream().findFirst().map(s -> s.getMetadata().get("gRPC_port")).get());
    }


    public void bannedById(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        CianUserServiceGrpc.CianUserServiceBlockingStub client = CianUserServiceGrpc.newBlockingStub(channel);
        client.bannedById(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
    }

    public void verifiedById(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        CianUserServiceGrpc.CianUserServiceBlockingStub client = CianUserServiceGrpc.newBlockingStub(channel);
        client.verifiedById(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
    }


}
