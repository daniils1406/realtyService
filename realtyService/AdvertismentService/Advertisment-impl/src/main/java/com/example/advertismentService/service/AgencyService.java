package com.example.advertismentService.service;

import dto.EntityPage;
import dto.request.agency.AgencyRequest;
import dto.request.agency.AgencyUpdateRequest;
import dto.response.agency.AgencyAdminResponse;
import dto.response.agency.AgencyResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AgencyService {

    EntityPage findAll(int page, List<String> status, List<String> level, Map<String, String> columnsAndOrder,
                       boolean isAdmin);

    AgencyResponse createAgency(AgencyRequest agencyRequest);

    AgencyResponse updateAgencyById(AgencyUpdateRequest agencyRequest);

    AgencyResponse findById(UUID agencyId);

    void deleteById(UUID agencyId);

    void approveById(UUID agencyId);

    void updateLevel(UUID agencyId, String newLevel);

    AgencyAdminResponse findByIdByAdmin(UUID id);
}
