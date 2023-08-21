package com.example.chatService.grpc;

import chat.*;
import com.example.chatService.constants.KafkaConstants;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.example.chatService.exception.InvalidRoomNameException;
import com.example.chatService.mapper.NotificationMapper;
import com.example.chatService.repository.ChatRoomRepository;
import com.example.chatService.service.ChatService;
import com.example.chatService.service.NotificationEntityService;
import com.google.protobuf.Any;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@GrpcService
@Component
@RequiredArgsConstructor
public class GrpcChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {

    private final KafkaTemplate<String, NotificationEntity> kafkaNotificationTemplate;

    private final NotificationMapper notificationMapper;

    private final ChatService chatService;

    private final ChatRoomRepository chatRoomRepository;

    private final NotificationEntityService notificationEntityService;


    @Override
    public void createNotificationRoom(NotificationRoom request, StreamObserver<Empty> responseObserver) {
        try {
            chatService.createNotificationRoom(UUID.fromString(request.getId()), request.getUserLogin());
            Empty empty = Empty.newBuilder().build();
            responseObserver.onNext(empty);
            responseObserver.onCompleted();
        } catch (InvalidRoomNameException ex) {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("Room with such name already exists")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("AlreadyExists")
                            .putMetadata("name", request.getUserLogin().toString())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void sendNotification(NotificationRequest request, StreamObserver<Empty> responseObserver) {
        dto.request.NotificationRequest notificationRequest = new dto.request.NotificationRequest(request.getCauseOfMessage(), request.getContent(), request.getRecipientId());
        notificationEntityService.saveNotification(notificationMapper.fromRequestToEntity(notificationRequest));
        try {
            kafkaNotificationTemplate.send(KafkaConstants.KAFKA_TOPIC, notificationMapper.fromRequestToEntity(notificationRequest)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        Empty empty = Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }


    @Override
    public void changeNameNotificationRoom(NotificationRoom request, StreamObserver<Empty> responseObserver) {
        chatService.updateNotificationRoomName(request.getUserLogin(), request.getId());
        chatRoomRepository.updateRoomName(request.getUserLogin(), request.getId());
        Empty empty = Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteNotificationRoom(IdRequest request, StreamObserver<Empty> responseObserver) {
        chatRoomRepository.deleteRoom(request.getId());
        Empty empty = Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }
}
