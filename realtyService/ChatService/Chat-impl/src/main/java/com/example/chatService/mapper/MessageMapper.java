package com.example.chatService.mapper;

import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import dto.request.MessageRequest;
import dto.response.MessageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {


    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createDate",ignore = true)
    @Mapping(target = "status",ignore = true)
    MessageEntity fromRequestToEntity(MessageRequest messageRequest);

    MessageResponse fromEntityToResponse(MessageEntity message);
}
