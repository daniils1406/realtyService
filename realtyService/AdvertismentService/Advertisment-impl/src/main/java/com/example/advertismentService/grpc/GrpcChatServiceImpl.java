package com.example.advertismentService.grpc;

import chat.ChatServiceGrpc;
import chat.Empty;
import chat.NotificationRequest;
import chat.NotificationRoom;
import document.CheckRequest;
import document.CheckResponse;
import document.FileServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class GrpcChatServiceImpl {

    private final DiscoveryClient discoveryClient;

    public void createNotification(String causeOfMessage, String content, UUID recipientId) {
        String documentServiceHost = discoveryClient.getInstances("chatService").stream().findFirst().map(s -> s.getHost()).get();

        int documentServiceGrpcPort = Integer.parseInt(discoveryClient.getInstances("chatService").stream().findFirst().map(s -> s.getMetadata().get("gRPC_port")).get());


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
}
