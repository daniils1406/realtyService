package com.example.advertismentService.service;

import dto.EntityPage;
import dto.request.residentialcomplex.ResidentialComplexRequest;
import dto.request.residentialcomplex.ResidentialComplexUpdateRequest;
import dto.response.residentialcomplex.ResidentialComplexAdminResponse;
import dto.response.residentialcomplex.ResidentialComplexResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ResidentialComplexService {
    EntityPage findAll(int page, List<String> status, Map<String, String> columnsAndOrder, boolean isAdmin);

    ResidentialComplexResponse createResidentialComplex(ResidentialComplexRequest residentialComplexRequest);

    ResidentialComplexResponse updateResidentialComplexById(ResidentialComplexUpdateRequest residentialComplexRequest);

    ResidentialComplexResponse findById(UUID residentialComplexId);

    void deleteById(UUID residentialComplexId);

    void approveById(UUID residentialComplexId);

    ResidentialComplexAdminResponse findByIdByAdmin(UUID id);
}
