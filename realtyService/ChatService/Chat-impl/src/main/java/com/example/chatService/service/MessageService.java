package com.example.chatService.service;

import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import dto.request.MessageUpdateRequest;
import dto.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    UUID save(MessageEntity message);

    List<MessageEntity> findChatMessages(String chatId);

    MessageEntity findById(UUID messageId);


    MessageResponse updateMessage(UUID userId,MessageUpdateRequest messageUpdateRequest);

    void deleteMessage(UUID userId,UUID id);
}
