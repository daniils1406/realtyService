package com.example.advertismentService.service;

import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.realty.IdAndRealtyStatusRequest;
import dto.request.realty.flat.RealtyFlatRequest;
import dto.request.realty.flat.RealtyFlatUpdateRequest;
import dto.response.realty.flat.RealtyFlatResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RealtyFlatService {
    EntityPage findAll(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                       Map<String, String> columnsAndOrder);

    EntityPage findAllForAgent(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                       Map<String, String> columnsAndOrder,UUID agentId);
    EntityPage findAllOfSomeOwner(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                       Map<String, String> columnsAndOrder,UUID ownerId);

    RealtyFlatResponse createFlat(RealtyFlatRequest flat);

    RealtyFlatResponse updateFlat(RealtyFlatUpdateRequest newFlat);

    RealtyFlatResponse findById(UUID flatId);

    void setStatusFlat(UUID flatId, String newStatus);

    void interestedInRealty(UUID userId,UUID realtyId);


    void transferRealtyToAgent(UUID realtyId, UUID agentId);
}
