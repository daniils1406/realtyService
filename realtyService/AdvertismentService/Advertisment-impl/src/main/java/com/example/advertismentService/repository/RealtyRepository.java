package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RealtyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface RealtyRepository {
    Page<RealtyEntity> findAll(Pageable pageable, List<String> realtyStatus, String realtyType, List<String> advertType, FlatOrHouse flatOrHouse);


    Page<RealtyEntity> findAllForAgent(Pageable pageable, List<String> realtyStatus, String realtyType, List<String> advertType, FlatOrHouse flatOrHouse,UUID agentId);

    Page<RealtyEntity> findAllOfOwner(Pageable pageable, List<String> realtyStatus, String realtyType, List<String> advertType, FlatOrHouse flatOrHouse, UUID ownerId);

    RealtyEntity save(RealtyEntity entity, FlatOrHouse flatOrHouse);

    RealtyEntity updateById(RealtyEntity entity, FlatOrHouse flatOrHouse);

    RealtyEntity findById(UUID id, FlatOrHouse flatOrHouse);

    void setStatus(UUID flatId, String newStatus);

    void deleteAllRealtyOfSomeOwner(UUID ownerId);

    void bannedAllRealtyOfSomeOwner(UUID ownerId);

    void verifyAllRealtyOfSomeOwner(UUID ownerId);

    boolean checkExists(UUID ownerId);

    void bannedAllRealtyOfResidentialComplex(UUID residentialComplexId);

    FlatOrHouse findOutTypeOfRealty(UUID realtyId);

    boolean checkExistsRealtyOfThisOwner(UUID ownerId);

    void transferRealtyToAgent(UUID realtyId,UUID agentId);
}
