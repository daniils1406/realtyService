package com.example.advertismentService.repository.impl;

import com.example.advertismentService.model.jooq.schema.Tables;
import com.example.advertismentService.model.jooq.schema.enums.Status;
import com.example.advertismentService.model.jooq.schema.tables.pojos.BuilderEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.BuilderRecord;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.builder.BuilderInsertingException;
import com.example.advertismentService.exception.builder.BuilderNotFoundException;
import com.example.advertismentService.mapper.jooq.BuilderJooqUnmapper;
import com.example.advertismentService.repository.BuilderRepository;
import com.example.advertismentService.repository.UtilRepository;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BuilderRepositoryImpl implements BuilderRepository, UtilRepository {

    private final DSLContext dslContext;

    private final BuilderJooqUnmapper builderJooqUnmapper;


    @Override
    public Page<BuilderEntity> findAll(Pageable pageable, List<String> status) {
        List<BuilderEntity> builders = dslContext.selectFrom(Tables.BUILDER_ENTITY)
                .where(Tables.BUILDER_ENTITY.STATUS.in(status))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Tables.BUILDER_ENTITY.getClass(), Tables.BUILDER_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(BuilderEntity.class);

        Long totalPages = dslContext.selectFrom(Tables.BUILDER_ENTITY)
                .where(Tables.BUILDER_ENTITY.STATUS.in(status))
                .stream().count();

        return new PageImpl<>(builders, pageable, totalPages);
    }

    @Override
    public BuilderEntity save(BuilderEntity entity) {
        BuilderRecord builderRecord = dslContext.newRecord(Tables.BUILDER_ENTITY, entity);
        builderRecord.setStatus(Status.REGISTERED);
        builderRecord.setInsertDate(LocalDate.now());
        return dslContext.insertInto(Tables.BUILDER_ENTITY)
                .set(builderRecord)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new BuilderInsertingException())
                .into(BuilderEntity.class);
    }

    @Override
    public BuilderEntity updateById(BuilderEntity entity) {
        return dslContext.update(Tables.BUILDER_ENTITY)
                .set(builderJooqUnmapper.unmap(entity))
                .where(Tables.BUILDER_ENTITY.ID.eq(entity.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new BuilderNotFoundException(entity.getId()))
                .into(BuilderEntity.class);
    }

    @Override
    public BuilderEntity findById(UUID id) {
        return dslContext.selectFrom(Tables.BUILDER_ENTITY)
                .where(Tables.BUILDER_ENTITY.ID.eq(id))
                .fetchOptionalInto(BuilderEntity.class)
                .orElseThrow(() -> new BuilderNotFoundException(id));
    }

    @Override
    public void deleteById(UUID id) {
        dslContext.update(Tables.BUILDER_ENTITY)
                .set(Tables.BUILDER_ENTITY.STATUS, Status.DELETE)
                .where(Tables.BUILDER_ENTITY.ID.eq(id))
                .execute();

    }

    @Override
    public void verifyById(UUID builderId) {
        dslContext.update(Tables.BUILDER_ENTITY)
                .set(Tables.BUILDER_ENTITY.STATUS, Status.VERIFIED)
                .where(Tables.BUILDER_ENTITY.ID.eq(builderId))
                .execute();
    }

    @Override
    public BuilderEntity findByName(String name) {
        boolean builderExist = dslContext.fetchExists(dslContext.selectOne()
                .from(Tables.BUILDER_ENTITY)
                .where(Tables.BUILDER_ENTITY.NAME.eq(name)));
        if (builderExist) {
            return dslContext.selectFrom(Tables.BUILDER_ENTITY)
                    .where(Tables.BUILDER_ENTITY.NAME.eq(name))
                    .fetchOne()
                    .into(BuilderEntity.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean checkExists(UUID id) {
        return dslContext.fetchExists(dslContext.selectFrom(Tables.BUILDER_ENTITY)
                .where(Tables.BUILDER_ENTITY.ID.eq(id)));
    }
}
