package com.example.authenticationService.rabbit;

import java.util.UUID;

public interface RabbitProducer {
    void sendMessageToVerifyUser(String login);

    void sendMessageToResetPassword(String login);

    void sendMessageToResetLogin(String oldLogin,String login);

    void sendNotification(String causeOfMessage, String content, UUID recipientId);
}
