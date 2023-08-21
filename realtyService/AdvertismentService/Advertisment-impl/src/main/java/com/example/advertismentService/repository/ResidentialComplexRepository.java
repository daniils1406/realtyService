package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.ResidentialComplexEntity;
import dto.response.residentialcomplex.ResidentialComplexResponse;

import java.util.List;
import java.util.UUID;

public interface ResidentialComplexRepository extends CRUDRepository<ResidentialComplexEntity, UUID>, UtilRepository {

    void approveById(UUID residentialComplexId);

    List<ResidentialComplexResponse> findResidentialComplexesOfSomeBuilder(UUID builderId);

    ResidentialComplexEntity findByName(String name);

    boolean existById(UUID residentialComplexId);

}
