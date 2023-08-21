package com.example.fileservice.grpc;

import advertisement.AdvertisementServiceGrpc;
import advertisement.IdRequest;
import advertisement.OwnerId;
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

    public void verifySomeRealtyOfUser(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        client.verifySomeRealtyOfUser(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
    }


    public void verifyAllRealtyOfUser(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        client.verifyAllRealtyOfUser(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
    }

    public void bannedAllRealtyOfUser(UUID id) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();
        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        client.bannedAllRealtyOfUser(IdRequest.newBuilder()
                .setId(id.toString())
                .build());
    }


    public UUID findOutOwnerId(UUID realtyId) {
        String advertisementServiceHost = getHostOfService();

        int advertisementServiceGrpcPort = getPortOfService();
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(advertisementServiceHost, advertisementServiceGrpcPort)
                .usePlaintext()
                .build();

        AdvertisementServiceGrpc.AdvertisementServiceBlockingStub client = AdvertisementServiceGrpc.newBlockingStub(channel);
        OwnerId ownerId = client.findOwnerOfRealty(IdRequest.newBuilder()
                .setId(realtyId.toString())
                .build());
        UUID result = UUID.fromString(ownerId.getId());
        channel.shutdownNow();
        return result;
    }
}
