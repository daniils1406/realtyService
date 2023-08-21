package com.example.authenticationService.repository;

import com.example.authenticationService.model.jooq.schema.tables.pojos.VerificationTokenEntity;

import java.util.UUID;

public interface VerificationTokenRepository {
    void save(VerificationTokenEntity entity);

    VerificationTokenEntity findById(UUID id);

    void deleteById(UUID id);
}
