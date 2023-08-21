package com.example.advertismentService.service;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.deal.DealRequest;
import dto.request.deal.DealUpdateRequest;
import dto.response.deal.DealResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DealService {
    EntityPage findAll(int page, List<String> status, Map<String, String> columnsAndOrder, boolean isAdmin);

    DealResponse createDeal(DealRequest dealRequest);

    DealResponse updateById(DealUpdateRequest dealRequest);

    DealResponse findById(UUID dealId);

    void setStatus(UUID dealId, String status);
}
