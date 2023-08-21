package com.example.chatService.mapper;

import com.example.chatService.model.jooq.schema.tables.pojos.ChatRoomEntity;
import dto.response.ChatRoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatRoomResponse fromEntityToResponse(ChatRoomEntity chatRoomEntity);
}
