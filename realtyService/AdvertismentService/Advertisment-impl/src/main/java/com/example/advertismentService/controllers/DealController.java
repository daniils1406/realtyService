package com.example.advertismentService.controllers;

import api.DealApi;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.deal.DealRequest;
import dto.request.deal.DealUpdateRequest;
import dto.response.deal.DealResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.service.DealService;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DealController implements DealApi {

    private final DealService dealService;

    @Override
    public EntityPage getAllDeal(int page, List<String> status, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return dealService.findAll(page, status, columnsAndOrder, false);
    }

    @Override
    public DealResponse createNewDeal(DealRequest dealRequest) {
        return dealService.createDeal(dealRequest);
    }

    @Override
    public DealResponse updateDealById(DealUpdateRequest dealRequest) {
        return dealService.updateById(dealRequest);
    }

    @Override
    public DealResponse getDealById(IdRequest dealId) {
        return dealService.findById(dealId.getId());
    }

    @Override
    public void setNewStatus(UUID dealId, String status) {
        dealService.setStatus(dealId, status);
    }

}
