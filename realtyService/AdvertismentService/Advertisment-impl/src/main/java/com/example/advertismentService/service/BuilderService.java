package com.example.advertismentService.service;

import dto.EntityPage;
import dto.request.builder.BuilderRequest;
import dto.request.builder.BuilderUpdateRequest;
import dto.response.builder.BuilderAdminWithComplexesResponse;
import dto.response.builder.BuilderResponse;
import dto.response.builder.BuilderWithComplexesResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BuilderService {

    EntityPage findAll(int page, List<String> status, Map<String, String> columnsAndOrder, boolean isAdmin);

    BuilderResponse createBuilder(BuilderRequest builderData);

    BuilderResponse updateBuilderById(BuilderUpdateRequest newBuilder);

    BuilderWithComplexesResponse findById(UUID builderId);

    void deleteById(UUID builderId);

    void approveById(UUID builderId);

    BuilderAdminWithComplexesResponse findByIdByAdmin(UUID id);
}
