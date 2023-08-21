package com.example.advertismentService.repository.impl;

import com.example.advertismentService.model.jooq.schema.Tables;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsAndAgencyEntity;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.repository.RegionAndAgencyRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RegionAndAgencyRepositoryImpl implements RegionAndAgencyRepository {

    private final DSLContext dslContext;

    @Override
    public List<Integer> findAllRegionsOfAgency(UUID id) {
        return dslContext.select(Tables.REGIONS_AND_AGENCY_ENTITY.REGION_CODE)
                .from(Tables.REGIONS_AND_AGENCY_ENTITY)
                .where(Tables.REGIONS_AND_AGENCY_ENTITY.AGENCY_ID.eq(id))
                .fetch()
                .into(Integer.class);
    }

    @Override
    public RegionsAndAgencyEntity addRegionToAgency(UUID agencyId, Integer regionCode) {
        return dslContext.insertInto(Tables.REGIONS_AND_AGENCY_ENTITY)
                .set(Tables.REGIONS_AND_AGENCY_ENTITY.AGENCY_ID, agencyId)
                .set(Tables.REGIONS_AND_AGENCY_ENTITY.REGION_CODE, regionCode)
                .returning()
                .fetchOne()
                .into(RegionsAndAgencyEntity.class);
    }

    public boolean agencyAlreadyInRegion(UUID agencyId, Integer regionCode) {
        try {
            (Objects.requireNonNull(dslContext.selectFrom(Tables.REGIONS_AND_AGENCY_ENTITY)
                    .where(Tables.REGIONS_AND_AGENCY_ENTITY.AGENCY_ID.eq(agencyId)
                            .and(Tables.REGIONS_AND_AGENCY_ENTITY.REGION_CODE.eq(regionCode)))
                    .fetchOne()))
                    .into(RegionsAndAgencyEntity.class);
        } catch (NullPointerException ex) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteRegionOfAgency(UUID agencyId, Integer regionCode) {
        dslContext.delete(Tables.REGIONS_AND_AGENCY_ENTITY)
                .where(Tables.REGIONS_AND_AGENCY_ENTITY.AGENCY_ID.eq(agencyId))
                .and(Tables.REGIONS_AND_AGENCY_ENTITY.REGION_CODE.eq(regionCode))
                .execute();
    }
}
