package com.example.chatService.controller;

import api.RoomApi;
import com.example.chatService.mapper.ChatMapper;
import com.example.chatService.model.jooq.schema.tables.pojos.ChatRoomEntity;
import com.example.chatService.security.utils.AuthorizationHeaderUtil;
import com.example.chatService.security.utils.JwtUtil;
import com.example.chatService.service.ChatService;
import dto.IdRequest;
import dto.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class RoomController implements RoomApi {

    private final ChatService chatService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final ChatMapper chatMapper;


    @Override
    public void createNotificationRoom(UUID userId, String nameOfRoom) {
        chatService.createNotificationRoom(userId, nameOfRoom);
    }


    @Override
    public void createRoom(HttpServletRequest request, String nameOfRoom) {

        chatService.createRoom(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), nameOfRoom);
    }

    @Override
    public ChatRoomResponse changeRoomName(HttpServletRequest request, String newName, String roomId) {
        return chatService.updateRoomName(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), newName, roomId);
    }

    @Override
    public List<ChatRoomResponse> getAllRoomsOfUser(IdRequest idRequest) {
        List<ChatRoomEntity> chats = chatService.findAllRoomsOfUser(idRequest.getId());
        List<ChatRoomResponse> responses = new LinkedList<>();
        for (ChatRoomEntity chatRoomEntity : chats) {
            responses.add(chatMapper.fromEntityToResponse(chatRoomEntity));
        }
        return responses;
    }

    @Override
    public void deleteRoom(HttpServletRequest request, String roomId) {
        chatService.deleteRoom(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), roomId);
    }

    @Override
    public void deleteMemberFromChat(HttpServletRequest request, UUID userId, String roomId) {
        chatService.deleteMemberChat(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), userId, roomId);
    }

    @Override
    public void addMemberToChat(HttpServletRequest request, UUID userId, String roomId) {
        chatService.addMemberChat(UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId()), userId, roomId);
    }

}
