package com.example.chatService.service;

import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;

import java.util.List;
import java.util.UUID;

public interface NotificationEntityService {
    UUID saveNotification(NotificationEntity notification);

    List<NotificationEntity> getAllNotificationOfUser(String userId);
}
