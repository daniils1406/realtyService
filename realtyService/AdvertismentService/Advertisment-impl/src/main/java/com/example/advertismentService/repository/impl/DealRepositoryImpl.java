package com.example.advertismentService.repository.impl;

import com.example.advertismentService.exception.deal.DealInsertingException;
import com.example.advertismentService.exception.deal.DealNotFoundException;
import com.example.advertismentService.mapper.jooq.DealJooqUnmapper;
import com.example.advertismentService.model.jooq.schema.enums.Dealstatus;
import com.example.advertismentService.model.jooq.schema.tables.Deal;
import com.example.advertismentService.model.jooq.schema.tables.pojos.DealEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.DealRecord;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.repository.DealRepository;
import com.example.advertismentService.repository.UtilRepository;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class DealRepositoryImpl implements DealRepository {

    private final DSLContext dslContext;

    private final DealJooqUnmapper dealJooqUnmapper;

    @Override
    public Page<DealEntity> findAll(PageRequest pageable, List<String> status) {
        List<DealEntity> builders = dslContext.selectFrom(Deal.DEAL_ENTITY)
                .where(Deal.DEAL_ENTITY.STATUS.in(status))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Deal.DEAL_ENTITY.getClass(), Deal.DEAL_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(DealEntity.class);

        Long totalPages = dslContext.selectFrom(Deal.DEAL_ENTITY)
                .where(Deal.DEAL_ENTITY.STATUS.in(status))
                .stream().count();

        return new PageImpl<>(builders, pageable, totalPages);
    }

    @Override
    public DealEntity save(DealEntity dealEntity) {
        DealRecord record = dslContext.newRecord(Deal.DEAL_ENTITY, dealEntity);
        record.setInsertDate(LocalDate.now());
        return dslContext.insertInto(Deal.DEAL_ENTITY)
                .set(record)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DealInsertingException())
                .into(DealEntity.class);
    }

    @Override
    public DealEntity updateById(DealEntity newDeal) {
        return dslContext.update(Deal.DEAL_ENTITY)
                .set(dealJooqUnmapper.unmap(newDeal))
                .where(Deal.DEAL_ENTITY.ID.eq(newDeal.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DealNotFoundException(newDeal.getId()))
                .into(DealEntity.class);
    }

    @Override
    public DealEntity findById(UUID dealId) {
        return dslContext.selectFrom(Deal.DEAL_ENTITY)
                .where(Deal.DEAL_ENTITY.ID.eq(dealId))
                .fetchOptional()
                .orElseThrow(() -> new DealNotFoundException(dealId))
                .into(DealEntity.class);
    }

    @Override
    public void setStatus(UUID dealId, String status) {
        dslContext.update(Deal.DEAL_ENTITY)
                .set(Deal.DEAL_ENTITY.STATUS, Dealstatus.valueOf(status))
                .where(Deal.DEAL_ENTITY.ID.eq(dealId))
                .execute();
    }


}
