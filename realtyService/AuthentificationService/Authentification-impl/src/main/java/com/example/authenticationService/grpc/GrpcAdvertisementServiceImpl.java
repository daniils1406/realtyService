package com.example.authenticationService.grpc;

import advertisement.*;
import document.CheckRequest;
import document.CheckResponse;
import document.FileServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GrpcAdvertisementServiceImpl {

    private final DiscoveryClient discoveryClient;

    private String getHostOfService() {
        return discoveryClient.getInstances("advertisementService").stream().findFirst().map(s -> s.getHost()).get();
    }

    private Integer getPortOfService() {
        return Integer.parseInt(discoveryClient.getInstances("advertisementService").stream().findFirst().map(s -> s.getMetadata().get("gRPC_port")).get());
    }

    public void deleteAllRealtyOfUser(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        client.deleteAllRealtyOfUser(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
        channel.shutdownNow();
    }

    public boolean checkAgencyExist(UUID organisationId) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        OrganisationExist organisationExist = client.checkOrganisationExist(OrganisationRequest.newBuilder()
                .setOrganisation(Organisation.AGENCY)
                .setId(organisationId.toString())
                .build());
        channel.shutdownNow();
        return organisationExist.getExist();
    }

    public boolean checkBuilderExist(UUID organisationId) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        OrganisationExist organisationExist = client.checkOrganisationExist(OrganisationRequest.newBuilder()
                .setOrganisation(Organisation.BUILDER)
                .setId(organisationId.toString())
                .build());
        channel.shutdownNow();
        return organisationExist.getExist();
    }
}
