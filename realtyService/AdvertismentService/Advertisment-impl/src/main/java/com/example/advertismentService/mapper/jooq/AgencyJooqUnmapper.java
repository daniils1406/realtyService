package com.example.advertismentService.mapper.jooq;

import com.example.advertismentService.model.jooq.schema.tables.Agency;
import com.example.advertismentService.model.jooq.schema.tables.pojos.AgencyEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.AgencyRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgencyJooqUnmapper implements RecordUnmapper<AgencyEntity, AgencyRecord> {

    private final DSLContext dslContext;

    @Override
    public AgencyRecord unmap(AgencyEntity source) throws MappingException {
        AgencyRecord record = dslContext.newRecord(Agency.AGENCY_ENTITY);
        record.setId(source.getId());
        record.setName(source.getName());
        record.setDescription(source.getDescription());
        record.setPhoneNumber(source.getPhoneNumber());
        record.setLinkForWebsite(source.getLinkForWebsite());
        return record;
    }
}
