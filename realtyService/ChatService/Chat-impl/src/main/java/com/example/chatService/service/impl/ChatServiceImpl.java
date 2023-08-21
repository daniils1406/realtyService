package com.example.chatService.service.impl;

import com.example.chatService.exception.NotAdminRoomException;
import com.example.chatService.exception.room.ImmutableRoomException;
import com.example.chatService.model.jooq.schema.enums.ChatStatus;
import com.example.chatService.model.jooq.schema.tables.pojos.ChatRoomEntity;
import com.example.chatService.exception.InvalidRoomNameException;
import com.example.chatService.repository.ChatMessageRepository;
import com.example.chatService.repository.ChatRoomRepository;
import com.example.chatService.service.ChatService;
import dto.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void createRoom(UUID userId, String name) {
        if (!chatRoomRepository.notificationRoomWithThisLoginAlreadyExists(name)) {
            String chatId = chatRoomRepository.createRoom(name);
            chatRoomRepository.addMemberToChat(userId, chatId);
            chatRoomRepository.assignNewChatAdmin(chatId, userId);
        } else {
            throw new InvalidRoomNameException();
        }
    }

    @Override
    public List<ChatRoomEntity> findAllRoomsOfUser(UUID idOfUser) {
        return chatRoomRepository.findAllRoomsOfUser(idOfUser);
    }

    @Override
    public List<UUID> findAllMembersOfChat(String roomId) {
        return chatRoomRepository.findAllMemberOfChat(roomId);
    }

    @Override
    public ChatRoomEntity findChatById(String chatId) {
        return chatRoomRepository.findRoomById(chatId);
    }

    @Override
    public ChatRoomResponse updateRoomName(UUID userId, String newName, String roomId) {
        if (chatRoomRepository.findOutChatStatus(roomId).equals(ChatStatus.CHAT) && chatRoomRepository.findMemberChat(userId, roomId).getIsadmin()) {
            return chatRoomRepository.updateRoomName(newName, roomId);
        } else {
            throw new ImmutableRoomException();
        }
    }

    @Override
    public ChatRoomResponse updateNotificationRoomName(String newName, String roomId) {
        return chatRoomRepository.updateRoomName(newName, roomId);
    }

    @Override
    public void deleteRoom(UUID userId, String id) {
        if (chatRoomRepository.findOutChatStatus(id).equals(ChatStatus.CHAT) && chatRoomRepository.findMemberChat(userId, id).getIsadmin()) {
            chatRoomRepository.deleteRoom(id);
        } else {
            throw new ImmutableRoomException();
        }
    }

    @Override
    public void deleteMemberChat(UUID currentUserId, UUID userId, String roomId) {
        if (currentUserId.equals(userId) || chatRoomRepository.findMemberChat(currentUserId, roomId).getIsadmin()) {

            chatRoomRepository.deleteMemberFromChat(userId, roomId);
        } else {
            throw new NotAdminRoomException();
        }
    }

    @Override
    public void addMemberChat(UUID currentUserId, UUID userId, String roomId) {
        if (chatRoomRepository.findMemberChat(currentUserId, roomId).getIsadmin()) {
            chatRoomRepository.addMemberToChat(userId, roomId);
        } else {
            throw new NotAdminRoomException();
        }
    }

    @Override
    public void createNotificationRoom(UUID userId, String nameOfRoom) {
        chatRoomRepository.createNotificationRoom(userId, nameOfRoom);
    }

    @Override
    public void messageDelivered(UUID messageId) {
        chatMessageRepository.messageDelivered(messageId);
    }

    @Override
    public void messageFailed(UUID messageId) {
        chatMessageRepository.messageFailed(messageId);
    }
}
