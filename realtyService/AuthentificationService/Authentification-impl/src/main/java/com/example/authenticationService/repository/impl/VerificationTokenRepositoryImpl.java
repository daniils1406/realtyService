package com.example.authenticationService.repository.impl;

import com.example.authenticationService.exception.verificationtoken.VerificationTokenNotFoundException;
import com.example.authenticationService.model.jooq.schema.tables.pojos.VerificationTokenEntity;
import com.example.authenticationService.repository.VerificationTokenRepository;
import com.example.authenticationService.model.jooq.schema.Tables;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    public final DSLContext dslContext;

    @Override
    public void save(VerificationTokenEntity entity) {
        dslContext.insertInto(Tables.VERIFICATION_TOKEN_ENTITY)
                .set(dslContext.newRecord(Tables.VERIFICATION_TOKEN_ENTITY, entity))
                .execute();
    }

    @Override
    public VerificationTokenEntity findById(UUID id) {
        return dslContext.selectFrom(Tables.VERIFICATION_TOKEN_ENTITY)
                .where(Tables.VERIFICATION_TOKEN_ENTITY.ID.eq(id))
                .fetchOptionalInto(VerificationTokenEntity.class)
                .orElseThrow(() -> new VerificationTokenNotFoundException(id));
    }

    @Override
    public void deleteById(UUID id) {
        dslContext.deleteFrom(Tables.VERIFICATION_TOKEN_ENTITY)
                .where(Tables.VERIFICATION_TOKEN_ENTITY.ID.eq(id))
                .execute();
    }
}
