package com.example.advertismentService.mapper.jooq;

import com.example.advertismentService.model.jooq.schema.tables.ResidentialComplex;
import com.example.advertismentService.model.jooq.schema.tables.pojos.ResidentialComplexEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.ResidentialComplexRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResidentialComplexJooqUnmapper implements RecordUnmapper<ResidentialComplexEntity, ResidentialComplexRecord> {

    private final DSLContext dslContext;

    @Override
    public ResidentialComplexRecord unmap(ResidentialComplexEntity source) throws MappingException {
        ResidentialComplexRecord record = dslContext.newRecord(ResidentialComplex.RESIDENTIAL_COMPLEX_ENTITY);
        record.setId(source.getId());
        record.setRegion(source.getRegion());
        record.setCity(source.getCity());
        record.setDistrict(source.getDistrict());
        record.setNumberOfBuildings(source.getNumberOfBuildings());
        record.setNumberOfReadyBuildings(source.getNumberOfReadyBuildings());
        record.setBuilderId(source.getBuilderId());
        record.setName(source.getName());
        record.setDescription(source.getDescription());
        record.setLinkOnWebsite(source.getLinkOnWebsite());
        record.setPhoneNumber(source.getPhoneNumber());
        record.setDeliveryYear(source.getDeliveryYear());
        return record;
    }
}
