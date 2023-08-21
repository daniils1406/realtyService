package com.example.chatService.service.impl;

import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import com.example.chatService.service.MessageService;
import com.example.chatService.exception.NotOwnerException;
import com.example.chatService.repository.ChatMessageRepository;
import dto.request.MessageUpdateRequest;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final ChatMessageRepository chatMessageRepository;


    @Override
    public UUID save(MessageEntity message) {
        return chatMessageRepository.save(message);
    }

    @Override
    public List<MessageEntity> findChatMessages(String chatId) {
        return chatMessageRepository.findChatMessages(chatId);
    }


    @Override
    public MessageEntity findById(UUID messageId) {
        return chatMessageRepository.findById(messageId);
    }

    @Override
    public MessageResponse updateMessage(UUID userId, MessageUpdateRequest messageUpdateRequest) {
        MessageEntity message = findById(messageUpdateRequest.getId());
        if (userId.equals(message.getSenderId())) {
            return chatMessageRepository.updateMessageContent(messageUpdateRequest.getContent(), messageUpdateRequest.getId());
        } else {
            throw new NotOwnerException();
        }
    }

    @Override
    public void deleteMessage(UUID userId, UUID id) {
        MessageEntity message = findById(id);
        if (userId.equals(message.getSenderId())) {
            chatMessageRepository.deleteMessage(id);
        } else {
            throw new NotOwnerException();
        }
    }


}
