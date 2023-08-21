package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsAndAgencyEntity;

import java.util.List;
import java.util.UUID;


public interface RegionAndAgencyRepository {
    List<Integer> findAllRegionsOfAgency(UUID id);

    RegionsAndAgencyEntity addRegionToAgency(UUID agencyId, Integer regionCode);


    void deleteRegionOfAgency(UUID agencyId, Integer regionCode);
}
