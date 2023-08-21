package com.example.chatService.consumer;

import com.example.chatService.constants.KafkaConstants;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@EnableKafka
@RequiredArgsConstructor
@KafkaListener(
        topics = KafkaConstants.KAFKA_TOPIC,
        groupId = KafkaConstants.GROUP_ID2,
        containerFactory = "kafkaListenerContainerFactoryNotification"
)
public class NotificationListener {
    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;


    @KafkaHandler
    public void listen(NotificationEntity notification) {
        try {
            template.convertAndSendToUser(
                    notification.getRecipientId().toString(), "/queue/messages",
                    objectMapper.writeValueAsString(new NotificationEntity(
                            notification.getId(),
                            notification.getCauseOfMessage(),
                            notification.getContent(),
                            LocalDate.now(),
                            notification.getRecipientId()
                    )));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaHandler(isDefault = true)
    public void handleOthers(Object others) {
        System.out.println("This message was wrap by other listener");
    }
}
