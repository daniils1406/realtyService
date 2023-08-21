package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.AgencyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AgencyRepository {

    AgencyEntity save(AgencyEntity entity);

    AgencyEntity updateById(AgencyEntity entity);

    AgencyEntity findById(UUID id);

    void deleteById(UUID id);

    Page<AgencyEntity> findAll(Pageable pageable, List<String> status, List<String> level);

    AgencyEntity findByName(String name);

    void approveById(UUID agencyId);

    void updateLevelById(UUID agencyId, String newLevel);

    boolean checkExists(UUID id);
}
