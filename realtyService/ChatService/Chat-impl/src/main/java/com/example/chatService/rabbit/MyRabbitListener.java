package com.example.chatService.rabbit;


import com.example.chatService.constants.KafkaConstants;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.example.chatService.mapper.NotificationMapper;
import com.example.chatService.service.NotificationEntityService;
import dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class MyRabbitListener {

    private final NotificationEntityService notificationEntityService;

    private final KafkaTemplate<String, NotificationEntity> kafkaNotificationTemplate;

    private final NotificationMapper notificationMapper;

    @RabbitListener(queues = "${service-rabbit.queueNotification-name}", ackMode = "AUTO")
    public void getMessageToRegistration(NotificationRequest request) {
        dto.request.NotificationRequest notificationRequest = new dto.request.NotificationRequest(request.getCauseOfMessage(), request.getContent(), request.getRecipientId());
        notificationEntityService.saveNotification(notificationMapper.fromRequestToEntity(notificationRequest));
        try {
            kafkaNotificationTemplate.send(KafkaConstants.KAFKA_TOPIC, notificationMapper.fromRequestToEntity(notificationRequest)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
