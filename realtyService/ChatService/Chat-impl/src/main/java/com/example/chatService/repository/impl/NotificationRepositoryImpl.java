package com.example.chatService.repository.impl;

import com.example.chatService.model.jooq.schema.tables.Notification;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import com.example.chatService.model.jooq.schema.tables.records.NotificationRecord;
import com.example.chatService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final DSLContext dslContext;

    @Override
    public UUID saveNotification(NotificationEntity notification) {
        NotificationRecord notificationRecord = dslContext.newRecord(Notification.NOTIFICATION_ENTITY, notification);
        notificationRecord.setCreateDate(LocalDate.now());
        UUID id = dslContext.insertInto(Notification.NOTIFICATION_ENTITY)
                .set(notificationRecord)
                .returning()
                .fetchOne(Notification.NOTIFICATION_ENTITY.ID);
        return id;
    }

    @Override
    public List<NotificationEntity> getAllNotificationOfUser(String userId) {
        return dslContext.selectFrom(Notification.NOTIFICATION_ENTITY)
                .where(Notification.NOTIFICATION_ENTITY.RECIPIENT_ID.eq(userId))
                .fetch()
                .into(NotificationEntity.class);
    }
}
