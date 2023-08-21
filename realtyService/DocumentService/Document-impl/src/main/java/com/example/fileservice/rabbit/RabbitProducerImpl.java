package com.example.fileservice.rabbit;

import com.example.fileservice.config.properties.RabbitProperties;
import dto.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitProducerImpl implements RabbitProducer {

    private final RabbitTemplate template;

    private final RabbitProperties properties;


    @Override
    public void sendNotification(String causeOfMessage, String content, UUID recipientId) {
        dto.request.NotificationRequest notificationRequest = NotificationRequest.builder()
                .recipientId(recipientId)
                .causeOfMessage(causeOfMessage)
                .content(content)
                .build();
        template.convertAndSend(properties.getExchangeNameNotification(),
                properties.getRoutingKeyNotification(), notificationRequest);
    }
}
