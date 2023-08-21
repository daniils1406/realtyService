package com.example.chatService.repository;

import com.example.chatService.model.jooq.schema.enums.ChatStatus;
import com.example.chatService.model.jooq.schema.tables.pojos.ChatAndUserEntity;
import com.example.chatService.model.jooq.schema.tables.pojos.ChatRoomEntity;
import dto.response.ChatRoomResponse;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository {
    String createRoom(String name);

    List<ChatRoomEntity> findAllRoomsOfUser(UUID idOfUser);

    void addMemberToChat(UUID userId,String roomId);

    void deleteMemberFromChat(UUID userId,String roomId);

    List<UUID> findAllMemberOfChat(String chatId);

    ChatRoomEntity findRoomById(String roomId);

    ChatRoomResponse updateRoomName(String newName, String roomId);

    void deleteRoom(String id);

    void assignNewChatAdmin(String chatId,UUID userId);

    void createNotificationRoom(UUID userId, String nameOfRoom);

    ChatStatus findOutChatStatus(String chatId);

    boolean notificationRoomWithThisLoginAlreadyExists(String name);

    ChatAndUserEntity findMemberChat(UUID userId, String chatId);

}
