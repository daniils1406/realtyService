package com.example.authenticationService.mapper.jooq;

import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.model.jooq.schema.tables.records.CianUserRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.RecordUnmapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.example.authenticationService.model.jooq.schema.Tables.CIAN_USER_ENTITY;

@Component
@RequiredArgsConstructor
public class CianUserUnmapper implements RecordUnmapper<CianUserEntity, CianUserRecord> {//я его создал для того, чтобы при update record не пытался обновить insertDate и оставлял прошлое значение

    private final DSLContext dslContext;

    @Override
    public CianUserRecord unmap(CianUserEntity source) throws MappingException {
        CianUserRecord record = dslContext.newRecord(CIAN_USER_ENTITY);
        record.setUpdateDate(LocalDate.now());
        record.setFirstName(source.getFirstName());
        record.setLastName(source.getLastName());
        record.setPatronymic(source.getPatronymic());
        record.setPhone(source.getPhone());
        return record;
    }
}
