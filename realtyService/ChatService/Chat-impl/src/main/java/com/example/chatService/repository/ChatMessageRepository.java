package com.example.chatService.repository;

import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import dto.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository {
    UUID save(MessageEntity message);

    List<MessageEntity> findChatMessages(String chatId);

    MessageEntity findById(UUID messageId);

    MessageResponse updateMessageContent(String content, UUID id);

    void deleteMessage(UUID id);

    void deleteMessageOfChat(String chatId);

    void messageDelivered(UUID messageId);

    void messageFailed(UUID messageId);

//    UUID saveNotification(NotificationEntity notification);
}
