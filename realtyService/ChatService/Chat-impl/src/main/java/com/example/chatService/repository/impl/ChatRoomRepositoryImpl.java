package com.example.chatService.repository.impl;

import com.example.chatService.model.jooq.schema.tables.pojos.ChatAndUserEntity;
import com.example.chatService.model.jooq.schema.tables.pojos.ChatRoomEntity;
import com.example.chatService.model.jooq.schema.tables.records.ChatRoomRecord;
import com.example.chatService.exception.room.RoomNotFoundException;
import com.example.chatService.model.jooq.schema.enums.ChatStatus;
import com.example.chatService.repository.ChatRoomRepository;
import dto.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.example.chatService.model.jooq.schema.tables.ChatAndUser.CHAT_AND_USER_ENTITY;
import static com.example.chatService.model.jooq.schema.tables.ChatRoom.CHAT_ROOM_ENTITY;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final DSLContext dslContext;

    @Override
    public String createRoom(String name) {
        ChatRoomRecord chatRoomRecord = new ChatRoomRecord();
        chatRoomRecord.setCreateDate(LocalDate.now());
        chatRoomRecord.setName(name);
        chatRoomRecord.setStatus(ChatStatus.CHAT);
        chatRoomRecord.setId(UUID.randomUUID().toString());
        return dslContext.insertInto(CHAT_ROOM_ENTITY)
                .set(chatRoomRecord)
                .returning()
                .fetchOne(CHAT_ROOM_ENTITY.ID);

    }

    @Override
    public List<ChatRoomEntity> findAllRoomsOfUser(UUID idOfUser) {
        List<String> rooms = dslContext.select()
                .from(CHAT_AND_USER_ENTITY)
                .join(CHAT_ROOM_ENTITY)
                .on(CHAT_ROOM_ENTITY.ID.eq(CHAT_AND_USER_ENTITY.CHAT_ID))
                .where(CHAT_AND_USER_ENTITY.USER_ID.eq(idOfUser))
                .and(CHAT_ROOM_ENTITY.STATUS.eq(ChatStatus.CHAT).or(CHAT_ROOM_ENTITY.STATUS.eq(ChatStatus.NOTIFICATION)))
                .fetch(CHAT_AND_USER_ENTITY.CHAT_ID);
        List<ChatRoomEntity> result = new LinkedList<>();
        for (String roomId : rooms) {
            result.add(findRoomById(roomId));
        }
        return result;
    }

    @Override
    public void addMemberToChat(UUID userId, String roomId) {
        dslContext.insertInto(CHAT_AND_USER_ENTITY)
                .set(CHAT_AND_USER_ENTITY.CHAT_ID, roomId)
                .set(CHAT_AND_USER_ENTITY.USER_ID, userId)
                .execute();
    }

    @Override
    public void deleteMemberFromChat(UUID userId, String roomId) {
        dslContext.deleteFrom(CHAT_AND_USER_ENTITY)
                .where(CHAT_AND_USER_ENTITY.USER_ID.eq(userId)
                        .and(CHAT_AND_USER_ENTITY.CHAT_ID.eq(roomId)))
                .execute();
    }

    @Override
    public List<UUID> findAllMemberOfChat(String chatId) {
        return dslContext.selectFrom(CHAT_AND_USER_ENTITY)
                .where(CHAT_AND_USER_ENTITY.CHAT_ID.eq(chatId))
                .fetch(CHAT_AND_USER_ENTITY.USER_ID);
    }

    @Override
    public ChatRoomEntity findRoomById(String roomId) {
        return dslContext.selectFrom(CHAT_ROOM_ENTITY)
                .where(CHAT_ROOM_ENTITY.ID.eq(roomId))
                .fetchOptionalInto(ChatRoomEntity.class)
                .orElseThrow(() -> new RoomNotFoundException());
    }

    @Override
    public ChatRoomResponse updateRoomName(String newName, String roomId) {
        return dslContext.update(CHAT_ROOM_ENTITY)
                .set(CHAT_ROOM_ENTITY.NAME, newName)
                .where(CHAT_ROOM_ENTITY.ID.eq(roomId))
                .returning()
                .fetchOptionalInto(ChatRoomResponse.class)
                .orElseThrow(() -> new RoomNotFoundException());
    }

    @Override
    public void deleteRoom(String id) {
        dslContext.update(CHAT_ROOM_ENTITY)
                .set(CHAT_ROOM_ENTITY.STATUS, ChatStatus.DELETED)
                .where(CHAT_ROOM_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public void assignNewChatAdmin(String chatId, UUID userId) {
        dslContext.update(CHAT_AND_USER_ENTITY)
                .set(CHAT_AND_USER_ENTITY.ISADMIN, false)
                .where(CHAT_AND_USER_ENTITY.CHAT_ID.eq(chatId))
                .and(CHAT_AND_USER_ENTITY.ISADMIN.eq(true))
                .execute();

        dslContext.update(CHAT_AND_USER_ENTITY)
                .set(CHAT_AND_USER_ENTITY.ISADMIN, true)
                .where(CHAT_AND_USER_ENTITY.CHAT_ID.eq(chatId))
                .and(CHAT_AND_USER_ENTITY.USER_ID.eq(userId))
                .execute();
    }

    @Override
    public void createNotificationRoom(UUID userId, String nameOfRoom) {
        ChatRoomRecord chatRoomRecord = new ChatRoomRecord(userId.toString(), LocalDate.now(), nameOfRoom, ChatStatus.NOTIFICATION);
        dslContext.insertInto(CHAT_ROOM_ENTITY)
                .set(chatRoomRecord)
                .execute();
    }

    @Override
    public ChatStatus findOutChatStatus(String chatId) {
        return dslContext.selectFrom(CHAT_ROOM_ENTITY)
                .where(CHAT_ROOM_ENTITY.ID.eq((chatId)))
                .fetchOne(CHAT_ROOM_ENTITY.STATUS);
    }

    @Override
    public boolean notificationRoomWithThisLoginAlreadyExists(String name) {
        return dslContext.fetchExists(dslContext.selectFrom(CHAT_ROOM_ENTITY)
                .where(CHAT_ROOM_ENTITY.NAME.eq(name))
                .and(CHAT_ROOM_ENTITY.STATUS.eq(ChatStatus.NOTIFICATION)));
    }

    @Override
    public ChatAndUserEntity findMemberChat(UUID userId, String chatId) {
        return dslContext.selectFrom(CHAT_AND_USER_ENTITY)
                .where(CHAT_AND_USER_ENTITY.USER_ID.eq(userId).and(CHAT_AND_USER_ENTITY.CHAT_ID.eq(chatId)))
                .fetchOneInto(ChatAndUserEntity.class);
    }

}
