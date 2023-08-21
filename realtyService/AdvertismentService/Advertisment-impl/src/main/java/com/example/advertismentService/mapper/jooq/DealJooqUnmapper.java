package com.example.advertismentService.mapper.jooq;

import com.example.advertismentService.model.jooq.schema.tables.Deal;
import com.example.advertismentService.model.jooq.schema.tables.pojos.DealEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.DealRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DealJooqUnmapper implements RecordUnmapper<DealEntity, DealRecord> {

    private final DSLContext dslContext;

    @Override
    public DealRecord unmap(DealEntity source) throws MappingException {
        DealRecord record = dslContext.newRecord(Deal.DEAL_ENTITY);
        record.setId(source.getId());
        record.setClientId(source.getClientId());
        record.setBrokerId(source.getBrokerId());
        record.setPeriodOfDeal(source.getPeriodOfDeal());
        record.setRealtyId(source.getRealtyId());
        record.setTransactionAmount(source.getTransactionAmount());
        record.setStatus(source.getStatus());
        record.setTransactionDate(source.getTransactionDate());
        return record;
    }
}
