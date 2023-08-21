package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.BuilderEntity;

import java.util.UUID;

public interface BuilderRepository extends CRUDRepository<BuilderEntity, UUID>, UtilRepository {
    void verifyById(UUID builderId);

    BuilderEntity findByName(String name);

    boolean checkExists(UUID id);
}
