package com.example.advertismentService.repository.impl;

import com.example.advertismentService.model.jooq.schema.Tables;
import com.example.advertismentService.model.jooq.schema.enums.Agentlevel;
import com.example.advertismentService.model.jooq.schema.enums.Status;
import com.example.advertismentService.model.jooq.schema.tables.pojos.AgencyEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.AgencyRecord;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.agency.AgencyInsertingException;
import com.example.advertismentService.exception.agency.AgencyNotFoundException;
import com.example.advertismentService.mapper.jooq.AgencyJooqUnmapper;
import com.example.advertismentService.repository.AgencyRepository;
import com.example.advertismentService.repository.UtilRepository;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AgencyRepositoryImpl implements AgencyRepository {

    private final DSLContext dslContext;

    private final AgencyJooqUnmapper agencyJooqUnmapper;

    @Override
    public Page<AgencyEntity> findAll(Pageable pageable, List<String> status, List<String> level) {
        List<AgencyEntity> agencys = dslContext.selectFrom(Tables.AGENCY_ENTITY)
                .where(Tables.AGENCY_ENTITY.STATUS.in(status))
                .and(Tables.AGENCY_ENTITY.LEVEL.in(level))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Tables.AGENCY_ENTITY.getClass(), Tables.AGENCY_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(AgencyEntity.class);

        Long totalPages = dslContext.selectFrom(Tables.AGENCY_ENTITY)
                .where(Tables.AGENCY_ENTITY.STATUS.in(status))
                .stream().count();
        return new PageImpl<>(agencys, pageable, totalPages);
    }

    @Override
    public AgencyEntity findByName(String name) {
        boolean agencyExist = dslContext.fetchExists(dslContext.selectOne()
                .from(Tables.AGENCY_ENTITY)
                .where(Tables.AGENCY_ENTITY.NAME.eq(name)));
        if (agencyExist) {
            return dslContext.selectFrom(Tables.AGENCY_ENTITY)
                    .where(Tables.AGENCY_ENTITY.NAME.eq(name))
                    .fetchOne()
                    .into(AgencyEntity.class);
        } else {
            return null;
        }
    }

    @Override
    public void approveById(UUID agencyId) {
        dslContext.update(Tables.AGENCY_ENTITY)
                .set(Tables.AGENCY_ENTITY.STATUS, Status.VERIFIED)
                .where(Tables.AGENCY_ENTITY.ID.eq(agencyId))
                .execute();
    }

    @Override
    public void updateLevelById(UUID agencyId, String newLevel) {
        dslContext.update(Tables.AGENCY_ENTITY)
                .set(Tables.AGENCY_ENTITY.LEVEL, Agentlevel.lookupLiteral(newLevel))
                .where(Tables.AGENCY_ENTITY.ID.eq(agencyId))
                .execute();
    }

    @Override
    public boolean checkExists(UUID id) {
        return dslContext.fetchExists(dslContext.selectFrom(Tables.AGENCY_ENTITY)
                .where(Tables.AGENCY_ENTITY.ID.eq(id)));
    }


    @Override
    public AgencyEntity save(AgencyEntity entity) {
        AgencyRecord agencyRecord = dslContext.newRecord(Tables.AGENCY_ENTITY, entity);
        agencyRecord.setStatus(Status.REGISTERED);
        agencyRecord.setInsertDate(LocalDate.now());
        agencyRecord.setLevel(Agentlevel.STARTING);
        return dslContext.insertInto(Tables.AGENCY_ENTITY)
                .set(agencyRecord)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new AgencyInsertingException())
                .into(AgencyEntity.class);
    }

    @Override
    public AgencyEntity updateById(AgencyEntity entity) {
        return dslContext.update(Tables.AGENCY_ENTITY)
                .set(agencyJooqUnmapper.unmap(entity))
                .where(Tables.AGENCY_ENTITY.ID.eq(entity.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new AgencyNotFoundException(entity.getId()))
                .into(AgencyEntity.class);
    }

    @Override
    public AgencyEntity findById(UUID id) {
        return dslContext.selectFrom(Tables.AGENCY_ENTITY)
                .where(Tables.AGENCY_ENTITY.ID.eq(id))
                .fetchOptionalInto(AgencyEntity.class)
                .orElseThrow(() -> new AgencyNotFoundException(id));
    }

    @Override
    public void deleteById(UUID id) {
        dslContext.update(Tables.AGENCY_ENTITY)
                .set(Tables.AGENCY_ENTITY.STATUS, Status.DELETE)
                .where(Tables.AGENCY_ENTITY.ID.eq(id))
                .execute();
    }
}
