package com.example.advertismentService.mapper.jooq;

import com.example.advertismentService.model.jooq.schema.tables.Builder;
import com.example.advertismentService.model.jooq.schema.tables.pojos.BuilderEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.BuilderRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuilderJooqUnmapper implements RecordUnmapper<BuilderEntity, BuilderRecord> {

    private final DSLContext dslContext;

    @Override
    public BuilderRecord unmap(BuilderEntity source) throws MappingException {
        BuilderRecord record = dslContext.newRecord(Builder.BUILDER_ENTITY);
        record.setId(source.getId());
        record.setName(source.getName());
        record.setDescription(source.getDescription());
        record.setLinkOnWebsite(source.getLinkOnWebsite());
        record.setPhoneNumber(source.getPhoneNumber());
        return record;
    }
}
