package com.example.authenticationService.service;

import dto.EntityPage;
import dto.request.cianuser.organisation.OrganisationRequest;
import dto.request.cianuser.organisation.OrganisationUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.organisation.OrganisationResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface OrganisationService {
    EntityPage findAll(int page, Map<String, String> columnsAndOrder, List<String> status);

    OrganisationResponse createNewOrganisation(OrganisationRequest organisationRequest);

    CianUserResponse updateOrganisation(OrganisationUpdateRequest organisationUpdateRequest);

    void deleteOrganisationById(UUID id);

    OrganisationResponse getOrganisationById(UUID id);

}
