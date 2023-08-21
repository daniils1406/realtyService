package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.DealEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface DealRepository {
    Page<DealEntity> findAll(PageRequest pageRequest, List<String> status);

    DealEntity save(DealEntity dealEntity);

    DealEntity updateById(DealEntity fromUpdateRequestToEntity);

    DealEntity findById(UUID dealId);

    void setStatus(UUID dealId, String status);
}
