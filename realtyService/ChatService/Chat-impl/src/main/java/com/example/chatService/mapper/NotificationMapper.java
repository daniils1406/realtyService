package com.example.chatService.mapper;


import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import dto.request.NotificationRequest;
import dto.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    NotificationEntity fromRequestToEntity(NotificationRequest notificationRequest);

    NotificationResponse fromEntityToResponse(NotificationEntity notification);
}
