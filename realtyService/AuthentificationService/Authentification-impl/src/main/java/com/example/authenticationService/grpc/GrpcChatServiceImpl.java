package com.example.authenticationService.grpc;

import chat.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GrpcChatServiceImpl {

    private final DiscoveryClient discoveryClient;

    private String getHostOfService() {
        return discoveryClient.getInstances("chatService").stream().findFirst().map(s -> s.getHost()).get();
    }

    private Integer getPortOfService() {
        return Integer.parseInt(discoveryClient.getInstances("chatService").stream().findFirst().map(s -> s.getMetadata().get("gRPC_port")).get());
    }


    public void createNotificationRoom(UUID userId, String userLogin) {
        String documentServiceHost = getHostOfService();
        int documentServiceGrpcPort = getPortOfService();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(documentServiceHost, documentServiceGrpcPort)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceBlockingStub client = ChatServiceGrpc.newBlockingStub(channel);
        client.createNotificationRoom(NotificationRoom.newBuilder()
                .setId(userId.toString())
                .setUserLogin(userLogin)
                .build());
        channel.shutdownNow();
    }

    public void createNotification(String causeOfMessage, String content, UUID recipientId) {
        String documentServiceHost = getHostOfService();

        int documentServiceGrpcPort = getPortOfService();


        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(documentServiceHost, documentServiceGrpcPort)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceBlockingStub client = ChatServiceGrpc.newBlockingStub(channel);
        client.sendNotification(NotificationRequest.newBuilder()
                .setCauseOfMessage(causeOfMessage)
                .setContent(content)
                .setRecipientId(recipientId.toString())
                .build());
        channel.shutdownNow();
    }

    public void changeNotificationRoomName(UUID userId, String newLogin) {
        String chatServiceHost = getHostOfService();

        int chatServiceGrpcPort = getPortOfService();


        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(chatServiceHost, chatServiceGrpcPort)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceBlockingStub client = ChatServiceGrpc.newBlockingStub(channel);

        client.changeNameNotificationRoom(NotificationRoom.newBuilder()
                .setId(userId.toString())
                .setUserLogin(newLogin)
                .build());

        channel.shutdownNow();
    }

    public void deleteNotificationRoomName(UUID userId) {
        String documentServiceHost = getHostOfService();

        int documentServiceGrpcPort = getPortOfService();


        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(documentServiceHost, documentServiceGrpcPort)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceBlockingStub client = ChatServiceGrpc.newBlockingStub(channel);

        client.deleteNotificationRoom(IdRequest.newBuilder()
                .setId(userId.toString())
                .build());

        channel.shutdownNow();
    }
}
