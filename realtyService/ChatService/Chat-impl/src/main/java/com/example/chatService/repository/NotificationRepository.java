package com.example.chatService.repository;

import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository {
    UUID saveNotification(NotificationEntity notification);

    List<NotificationEntity> getAllNotificationOfUser(String userId);
}
