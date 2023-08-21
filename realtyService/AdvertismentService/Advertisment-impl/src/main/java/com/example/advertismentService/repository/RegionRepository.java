package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsEntity;

public interface RegionRepository {
    RegionsEntity findByCode(int code);

    RegionsEntity findByName(String name);
}
