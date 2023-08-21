package com.example.chatService.service;

import com.example.chatService.model.jooq.schema.tables.pojos.ChatRoomEntity;
import dto.response.ChatRoomResponse;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    void createRoom(UUID userId,String name);

    List<ChatRoomEntity> findAllRoomsOfUser(UUID idOfUser);

    List<UUID> findAllMembersOfChat(String roomId);

    ChatRoomEntity findChatById(String chatId);

    ChatRoomResponse updateRoomName(UUID userId,String newName, String roomId);

    ChatRoomResponse updateNotificationRoomName(String newName,String roomId);

    void deleteRoom(UUID userId,String id);

    void deleteMemberChat(UUID currentUserId,UUID userId, String roomId);

    void addMemberChat(UUID currentUserId,UUID userId, String roomId);

    void createNotificationRoom(UUID userId, String nameOfRoom);

    void messageDelivered(UUID messageId);

    void messageFailed(UUID messageId);
}
