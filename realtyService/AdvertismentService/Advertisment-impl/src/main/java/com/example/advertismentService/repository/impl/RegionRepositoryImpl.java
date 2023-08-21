package com.example.advertismentService.repository.impl;

import com.example.advertismentService.model.jooq.schema.Tables;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsEntity;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.repository.RegionRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepository {

    private final DSLContext dslContext;

    @Override
    public RegionsEntity findByCode(int code) {
        RegionsEntity entity = dslContext.selectFrom(Tables.REGIONS_ENTITY)
                .where(Tables.REGIONS_ENTITY.CODE.eq(code))
                .fetchOne()
                .into(RegionsEntity.class);
        return entity;
    }

    @Override
    public RegionsEntity findByName(String name) {
        return dslContext.selectFrom(Tables.REGIONS_ENTITY)
                .where(Tables.REGIONS_ENTITY.REGION_NAME.eq(name))
                .fetchOne()
                .into(RegionsEntity.class);
    }
}
