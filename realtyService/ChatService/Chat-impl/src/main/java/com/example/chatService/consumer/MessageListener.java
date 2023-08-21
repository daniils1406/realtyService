package com.example.chatService.consumer;

import com.example.chatService.constants.KafkaConstants;
import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.example.chatService.model.jooq.schema.enums.Cause;
import com.example.chatService.model.jooq.schema.enums.Status;
import com.example.chatService.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@EnableKafka
@RequiredArgsConstructor
@KafkaListener(
        topics = KafkaConstants.KAFKA_TOPIC,
        groupId = KafkaConstants.GROUP_ID1,
        containerFactory = "kafkaListenerContainerFactory"
)
public class MessageListener {
    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @KafkaHandler
    public void listen(MessageEntity chatMessage) {
        List<UUID> allMembersOfChat = chatService.findAllMembersOfChat(chatMessage.getRecipientId().toString());
        try {
            for (UUID memberId : allMembersOfChat) {
                if (memberId.equals(chatMessage.getSenderId())) {
                    continue;
                }
                template.convertAndSendToUser(
                        memberId.toString(), "/queue/messages",
                        objectMapper.writeValueAsString(new NotificationEntity(
                                UUID.randomUUID(),
                                Cause.OPENED,
                                "You get a new message from" + chatService.findChatById(chatMessage.getRecipientId().toString()).getName().toString(),
                                LocalDate.now(),
                                chatMessage.getSenderId().toString())));
            }
            template.convertAndSendToUser(
                    chatMessage.getRecipientId().toString(), "/queue/messages",
                    objectMapper.writeValueAsString(new MessageEntity(
                            chatMessage.getId(),
                            chatMessage.getSenderId(),
                            chatMessage.getRecipientId(),
                            "Here will be name of sender get by grpc",
                            chatMessage.getContent(),
                            LocalDate.now(),
                            Status.SENDING
                    )));
            chatService.messageDelivered(chatMessage.getId());
        } catch (JsonProcessingException e) {
            chatService.messageFailed(chatMessage.getId());
            throw new RuntimeException(e);
        }
    }

    @KafkaHandler(isDefault = true)
    public void handleOthers(Object others) {
        System.out.println("This message was wrap by other listener");
    }
}