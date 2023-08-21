package com.example.advertismentService.repository.impl;

import com.example.advertismentService.mapper.jooq.ResidentialComplexJooqUnmapper;
import com.example.advertismentService.model.jooq.schema.Tables;
import com.example.advertismentService.model.jooq.schema.enums.Status;
import com.example.advertismentService.model.jooq.schema.tables.pojos.ResidentialComplexEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.ResidentialComplexRecord;
import dto.response.residentialcomplex.ResidentialComplexResponse;
import lombok.RequiredArgsConstructor;


import com.example.advertismentService.exception.residentialcomplex.ResidentialComplexNotFoundException;

import com.example.advertismentService.repository.ResidentialComplexRepository;
import com.example.advertismentService.repository.UtilRepository;
import org.jooq.DSLContext;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ResidentialComplexRepositoryImpl implements ResidentialComplexRepository {

    private final DSLContext dslContext;

    private final ResidentialComplexJooqUnmapper residentialComplexJooqUnmapper;

    @Override
    public Page<ResidentialComplexEntity> findAll(Pageable pageable, List<String> status) {
        List<ResidentialComplexEntity> residentialComplexes = dslContext.selectFrom(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.STATUS.in(status))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Tables.RESIDENTIAL_COMPLEX_ENTITY.getClass(),
                        Tables.RESIDENTIAL_COMPLEX_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(ResidentialComplexEntity.class);

        Long totalPages = dslContext.selectFrom(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.STATUS.in(status))
                .stream().count();
        return new PageImpl<>(residentialComplexes, pageable, totalPages);
    }

    @Override
    public ResidentialComplexEntity save(ResidentialComplexEntity entity) {
        ResidentialComplexRecord residentialComplexRecord = dslContext.newRecord(Tables.RESIDENTIAL_COMPLEX_ENTITY, entity);
        residentialComplexRecord.setStatus(Status.REGISTERED);
        return dslContext.insertInto(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .set(residentialComplexRecord)
                .returning()
                .fetchOne()
                .into(ResidentialComplexEntity.class);
    }

    @Override
    public ResidentialComplexEntity updateById(ResidentialComplexEntity entity) {
        return dslContext.update(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .set(residentialComplexJooqUnmapper.unmap(entity))
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.ID.eq(entity.getId()))
                .returning()
                .fetchOne()
                .into(ResidentialComplexEntity.class);
    }

    @Override
    public ResidentialComplexEntity findById(UUID id) {
        return dslContext.selectFrom(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.ID.eq(id))
                .fetchOptionalInto(ResidentialComplexEntity.class)
                .orElseThrow(() -> new ResidentialComplexNotFoundException(id));
    }

    @Override
    public void deleteById(UUID id) {
        dslContext.update(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .set(Tables.RESIDENTIAL_COMPLEX_ENTITY.STATUS, Status.DELETE)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.ID.eq(id))
                .execute();
    }


    @Override
    public void approveById(UUID residentialComplexId) {
        dslContext.update(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .set(Tables.RESIDENTIAL_COMPLEX_ENTITY.STATUS, Status.VERIFIED)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.ID.eq(residentialComplexId))
                .execute();
    }

    @Override
    public List<ResidentialComplexResponse> findResidentialComplexesOfSomeBuilder(UUID builderId) {
        return dslContext.selectFrom(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.BUILDER_ID.eq(builderId))
                .and(Tables.RESIDENTIAL_COMPLEX_ENTITY.STATUS.eq(Status.VERIFIED))
                .fetch()
                .into(ResidentialComplexResponse.class);
    }

    @Override
    public ResidentialComplexEntity findByName(String name) {
        boolean residentialComplexExist = dslContext.fetchExists(dslContext.selectOne()
                .from(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.NAME.eq(name)));
        if (residentialComplexExist) {
            return dslContext.selectFrom(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                    .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.NAME.eq(name))
                    .fetchOne()
                    .into(ResidentialComplexEntity.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean existById(UUID residentialComplexId) {
        return dslContext.fetchExists(dslContext.selectFrom(Tables.RESIDENTIAL_COMPLEX_ENTITY)
                .where(Tables.RESIDENTIAL_COMPLEX_ENTITY.ID.eq(residentialComplexId)));
    }

}
