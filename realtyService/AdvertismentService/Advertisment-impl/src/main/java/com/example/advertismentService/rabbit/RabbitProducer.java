package com.example.advertismentService.rabbit;

import java.util.UUID;

public interface RabbitProducer {
    void sendNotification(String causeOfMessage, String content, UUID recipientId);
}
