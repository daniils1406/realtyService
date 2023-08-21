package com.example.chatService.controller;

import api.MessageApi;
import com.example.chatService.constants.KafkaConstants;
import com.example.chatService.exception.NotOwnerException;
import com.example.chatService.exception.room.NotYourException;
import com.example.chatService.mapper.ChatMapper;
import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.example.chatService.mapper.MessageMapper;
import com.example.chatService.mapper.NotificationMapper;
import com.example.chatService.security.utils.AuthorizationHeaderUtil;
import com.example.chatService.security.utils.JwtUtil;
import com.example.chatService.service.impl.MessageServiceImpl;
import com.example.chatService.service.ChatService;
import com.example.chatService.service.NotificationEntityService;
import dto.IdRequest;
import dto.request.MessageRequest;
import dto.request.MessageUpdateRequest;
import dto.request.NotificationRequest;
import dto.response.MessageResponse;
import dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final MessageServiceImpl chatMessageService;
    private final NotificationEntityService notificationEntityService;
    private final ChatService chatService;
    private final KafkaTemplate<String, MessageEntity> kafkaMessageTemplate;
    private final KafkaTemplate<String, NotificationEntity> kafkaNotificationTemplate;
    private final MessageMapper messageMapper;
    private final NotificationMapper notificationMapper;
    private final ChatMapper chatMapper;
    private final JwtUtil jwtUtil;
    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    @Override
    public void sendMessage(HttpServletRequest request, MessageRequest messageRequest) {
        MessageEntity message = messageMapper.fromRequestToEntity(messageRequest);
        if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId().equals(messageRequest.getSenderId().toString())) {

            UUID messageId = chatMessageService.save(message);
            message.setId(messageId);
            try {
                kafkaMessageTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new NotOwnerException();
        }
    }

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        NotificationEntity notification = notificationMapper.fromRequestToEntity(notificationRequest);
        UUID id = notificationEntityService.saveNotification(notification);
        notification.setId(id);
        try {
            kafkaNotificationTemplate.send(KafkaConstants.KAFKA_TOPIC, notification).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MessageResponse changeMessage(HttpServletRequest request, MessageUpdateRequest messageUpdateRequest) {
        return chatMessageService.updateMessage(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), messageUpdateRequest);
    }

    @Override
    public void deleteMessage(HttpServletRequest request, IdRequest messageId) {
        chatMessageService.deleteMessage(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), messageId.getId());
    }

    @Override
    public List<MessageResponse> findChatMessages(String roomId) {
        List<MessageEntity> messageEntities = chatMessageService.findChatMessages(roomId);
        List<MessageResponse> messageResponses = new LinkedList<>();
        for (MessageEntity message : messageEntities) {
            messageResponses.add(messageMapper.fromEntityToResponse(message));
        }
        return messageResponses;
    }

    @Override
    public List<NotificationResponse> getAllNotificationsOfUser(HttpServletRequest request, String userId) {
        if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId().equals(userId)) {
            List<NotificationEntity> notificationEntities = notificationEntityService.getAllNotificationOfUser(userId);
            List<NotificationResponse> notificationResponses = new LinkedList<>();
            for (NotificationEntity notification : notificationEntities) {
                notificationResponses.add(notificationMapper.fromEntityToResponse(notification));
            }
            return notificationResponses;
        } else {
            throw new NotYourException();
        }
    }
}
