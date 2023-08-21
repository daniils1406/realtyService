package com.example.advertismentService.service;

import dto.EntityPage;
import dto.request.realty.home.RealtyHomeRequest;
import dto.request.realty.home.RealtyHomeUpdateRequest;
import dto.response.realty.home.RealtyHomeResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RealtyHomeService {
    EntityPage findAll(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                       Map<String, String> columnsAndOrder);

    EntityPage findAllForAgent(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                       Map<String, String> columnsAndOrder,UUID agentId);
    EntityPage findAllOfSomeOwner(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                                  Map<String, String> columnsAndOrder, UUID ownerId);

    RealtyHomeResponse createHome(RealtyHomeRequest home);

    RealtyHomeResponse updateHome(RealtyHomeUpdateRequest newHome);

    RealtyHomeResponse findById(UUID id);

    void setStatusHome(UUID id, String newStatus);

    void interestedInRealty(UUID userId,UUID realtyId);

    void transferRealtyToAgent(UUID realtyId, UUID agentId);
}
