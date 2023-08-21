package com.example.chatService.repository.impl;

import com.example.chatService.exception.message.MessageNotFoundException;
import com.example.chatService.model.jooq.schema.enums.Status;
import com.example.chatService.model.jooq.schema.tables.Message;
import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import com.example.chatService.model.jooq.schema.tables.records.MessageRecord;
import com.example.chatService.repository.ChatMessageRepository;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    private final DSLContext dslContext;

    @Override
    public UUID save(MessageEntity message) {
        MessageRecord messageRecord = dslContext.newRecord(Message.MESSAGE_ENTITY, message);
        messageRecord.setCreateDate(LocalDate.now());
        messageRecord.setStatus(Status.SENDING);
        return dslContext.insertInto(Message.MESSAGE_ENTITY)
                .set(messageRecord)
                .returning()
                .fetchOne(Message.MESSAGE_ENTITY.ID);
    }

    @Override
    public List<MessageEntity> findChatMessages(String chatId) {
        return dslContext.selectFrom(Message.MESSAGE_ENTITY)
                .where(Message.MESSAGE_ENTITY.RECIPIENT_ID.eq(chatId))
                .and(Message.MESSAGE_ENTITY.STATUS.eq(Status.DELIVERED))
                .fetch()
                .into(MessageEntity.class);
    }

    @Override
    public MessageEntity findById(UUID messageId) {
        return dslContext.selectFrom(Message.MESSAGE_ENTITY)
                .where(Message.MESSAGE_ENTITY.ID.eq(messageId))
                .and(Message.MESSAGE_ENTITY.STATUS.eq(Status.DELIVERED).or(Message.MESSAGE_ENTITY.STATUS.eq(Status.SENDING)))
                .fetchOptionalInto(MessageEntity.class)
                .orElseThrow(() -> new MessageNotFoundException());
    }

    @Override
    public MessageResponse updateMessageContent(String content, UUID id) {
        return dslContext.update(Message.MESSAGE_ENTITY)
                .set(Message.MESSAGE_ENTITY.CONTENT, content)
                .where(Message.MESSAGE_ENTITY.ID.eq(id))
                .returning()
                .fetchOptionalInto(MessageResponse.class)
                .orElseThrow(() -> new MessageNotFoundException());
    }

    @Override
    public void deleteMessage(UUID id) {
        dslContext.update(Message.MESSAGE_ENTITY)
                .set(Message.MESSAGE_ENTITY.STATUS, Status.DELETED)
                .where(Message.MESSAGE_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteMessageOfChat(String chatId) {
        dslContext.update(Message.MESSAGE_ENTITY)
                .set(Message.MESSAGE_ENTITY.STATUS, Status.DELETED)
                .where(Message.MESSAGE_ENTITY.RECIPIENT_ID.eq(chatId))
                .execute();
    }

    @Override
    public void messageDelivered(UUID messageId) {
        dslContext.update(Message.MESSAGE_ENTITY)
                .set(Message.MESSAGE_ENTITY.STATUS, Status.DELIVERED)
                .where(Message.MESSAGE_ENTITY.ID.eq(messageId))
                .execute();
    }

    @Override
    public void messageFailed(UUID messageId) {
        dslContext.update(Message.MESSAGE_ENTITY)
                .set(Message.MESSAGE_ENTITY.STATUS, Status.FAILED)
                .where(Message.MESSAGE_ENTITY.ID.eq(messageId))
                .execute();
    }
}
