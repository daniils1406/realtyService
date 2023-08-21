package com.example.chatService.entity;

import com.example.chatService.model.jooq.schema.enums.Cause;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestNotificationEntity {
    private Cause causeOfMessage;
    private String content;
    private LocalDate createDate;
    private UUID recipientId;
}
