package com.example.authenticationService.rabbit;

import com.example.authenticationService.config.properties.RabbitProperties;
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
    public void sendMessageToVerifyUser(String login) {
        template.convertAndSend(properties.getExchangeNameRegistration(),
                properties.getRoutingKeyRegistration(),
                login);
    }

    @Override
    public void sendMessageToResetPassword(String login) {
        template.convertAndSend(properties.getExchangeNameResetPassword(),
                properties.getRoutingKeyResetPassword(),
                login);
    }

    @Override
    public void sendMessageToResetLogin(String oldLogin, String login) {
        template.convertAndSend(properties.getExchangeNameResetLogin(),
                properties.getRoutingKeyResetLogin(),
                login);
    }

    @Override
    public void sendNotification(String causeOfMessage, String content, UUID recipientId) {

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .recipientId(recipientId)
                .causeOfMessage(causeOfMessage)
                .content(content)
                .build();
        template.convertAndSend(properties.getExchangeNameNotification(),
                properties.getRoutingKeyNotification(), notificationRequest);
    }
}
