package com.example.authenticationService.repository;

import com.example.authenticationService.model.jooq.schema.tables.pojos.RefreshTokenEntity;

import java.util.UUID;

public interface RefreshTokenRepository {
    void save(RefreshTokenEntity entity);

    RefreshTokenEntity findById(UUID id);

    RefreshTokenEntity findByUserId(UUID id);

    void deleteByUserId(UUID id);

    boolean userIdExists(UUID userId);

    void updateRefreshTokenOfUser(UUID userId,UUID newToken);
}
