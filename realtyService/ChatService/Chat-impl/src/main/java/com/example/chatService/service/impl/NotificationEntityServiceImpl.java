package com.example.chatService.service.impl;

import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.example.chatService.repository.NotificationRepository;
import com.example.chatService.service.NotificationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationEntityServiceImpl implements NotificationEntityService {

    private final NotificationRepository notificationRepository;

    @Override
    public UUID saveNotification(NotificationEntity notification) {
        UUID id = notificationRepository.saveNotification(notification);
        return id;
    }

    @Override
    public List<NotificationEntity> getAllNotificationOfUser(String userId) {
        return notificationRepository.getAllNotificationOfUser(userId);
    }
}
