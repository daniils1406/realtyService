package com.example.authenticationService.repository.impl;

import com.example.authenticationService.exception.refreshtoken.RefreshTokenNotFound;
import com.example.authenticationService.model.jooq.schema.tables.pojos.RefreshTokenEntity;
import com.example.authenticationService.repository.RefreshTokenRepository;
import com.example.authenticationService.model.jooq.schema.Tables;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final DSLContext dslContext;

    @Override
    public void save(RefreshTokenEntity entity) {
        dslContext.insertInto(Tables.REFRESH_TOKEN_ENTITY)
                .set(dslContext.newRecord(Tables.REFRESH_TOKEN_ENTITY, entity))
                .execute();
    }

    @Override
    public RefreshTokenEntity findById(UUID id) {
        return dslContext.selectFrom(Tables.REFRESH_TOKEN_ENTITY)
                .where(Tables.REFRESH_TOKEN_ENTITY.TOKEN.eq(id))
                .fetchOptionalInto(RefreshTokenEntity.class)
                .orElseThrow(() -> new RefreshTokenNotFound(id, "id"));
    }

    @Override
    public RefreshTokenEntity findByUserId(UUID id) {
        return dslContext.selectFrom(Tables.REFRESH_TOKEN_ENTITY)
                .where(Tables.REFRESH_TOKEN_ENTITY.USER_ID.eq(id))
                .fetchOptionalInto(RefreshTokenEntity.class)
                .orElseThrow(() -> new RefreshTokenNotFound(id, "id of user"));
    }

    @Override
    public void deleteByUserId(UUID id) {
        dslContext.deleteFrom(Tables.REFRESH_TOKEN_ENTITY)
                .where(Tables.REFRESH_TOKEN_ENTITY.USER_ID.eq(id))
                .execute();
    }

    @Override
    public boolean userIdExists(UUID userId) {
        return dslContext.fetchExists(dslContext.selectFrom(Tables.REFRESH_TOKEN_ENTITY)
                .where(Tables.REFRESH_TOKEN_ENTITY.USER_ID.eq(userId)));
    }

    @Override
    public void updateRefreshTokenOfUser(UUID userId, UUID newToken) {
        dslContext.update(Tables.REFRESH_TOKEN_ENTITY)
                .set(Tables.REFRESH_TOKEN_ENTITY.TOKEN, newToken)
                .where(Tables.REFRESH_TOKEN_ENTITY.USER_ID.eq(userId))
                .execute();
    }


}
