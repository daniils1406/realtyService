package com.example.advertismentService.grpc;

import authentication.CianUserResponse;
import authentication.CianUserServiceGrpc;
import authentication.CompanyIdPersonBelongsTo;
import authentication.IdRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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

    public UUID findCompanyWhichPersonBelong(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        CianUserServiceGrpc.CianUserServiceBlockingStub client = CianUserServiceGrpc.newBlockingStub(channel);
        CompanyIdPersonBelongsTo companyIdPersonBelongsTo = client.findCompanyWhichPersonBelong(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
        return UUID.fromString(companyIdPersonBelongsTo.getId());
    }

    public void changeClientToOwner(UUID ownerId) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        CianUserServiceGrpc.CianUserServiceBlockingStub client = CianUserServiceGrpc.newBlockingStub(channel);
        client.changeClientToOwner(IdRequest.newBuilder()
                .setId(ownerId.toString())
                .build());
        channel.shutdownNow();
    }


    public CianUserResponse findUserById(UUID userId) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        CianUserServiceGrpc.CianUserServiceBlockingStub client = CianUserServiceGrpc.newBlockingStub(channel);
        CianUserResponse cianUserResponse = client.findById(IdRequest.newBuilder()
                .setId(userId.toString())
                .build());
        channel.shutdownNow();
        return cianUserResponse;
    }


}
