package com.example.advertismentService.mapper.jooq;

import com.example.advertismentService.model.jooq.schema.tables.Realty;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RealtyEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.RealtyRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealtyJooqUnmapper implements RecordUnmapper<RealtyEntity, RealtyRecord> {

    private final DSLContext dslContext;


    @Override
    public RealtyRecord unmap(RealtyEntity source) throws MappingException {
        RealtyRecord record = dslContext.newRecord(Realty.REALTY_ENTITY);
        record.setId(source.getId());
        record.setReleaseDate(source.getReleaseDate());
        record.setComplexId(source.getComplexId());
        record.setOwnerId(source.getOwnerId());
        record.setSquare(source.getSquare());
        record.setRegion(source.getRegion());
        record.setDistrict(source.getDistrict());
        record.setAddress(source.getAddress());
        record.setDescription(source.getDescription());
        record.setAdvertType(source.getAdvertType());
        record.setCost(source.getCost());
        record.setTariffType(source.getTariffType());
        record.setFlatOrHouse(source.getFlatOrHouse());
        record.setStatus(source.getStatus());
        return record;
    }
}
