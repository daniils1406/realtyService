package com.example.advertismentService.service.impl;

import com.example.advertismentService.mapper.DealMapper;
import com.example.advertismentService.model.jooq.schema.enums.Dealstatus;
import com.example.advertismentService.model.jooq.schema.tables.pojos.DealEntity;
import dto.EntityPage;
import dto.request.deal.DealRequest;
import dto.request.deal.DealUpdateRequest;
import dto.response.deal.DealResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.repository.DealRepository;
import com.example.advertismentService.repository.RealtyRepository;
import com.example.advertismentService.service.DealService;
import com.example.advertismentService.util.PageRequestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    private final DealMapper mapper;

    private final RealtyRepository realtyRepository;


    @Override
    public EntityPage findAll(int page, List<String> status, Map<String, String> columnsAndOrder, boolean isAdmin) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<DealEntity> dealPage = dealRepository.findAll(pageRequest, status);

        return EntityPage.builder()
                .totalPages(dealPage.getTotalPages())
                .data(dealPage.getContent().stream()
                        .map(entity -> {
                            if (isAdmin) {
                                return mapper.fromEntityToResponse(entity);
                            } else {
                                return mapper.fromEntityToResponse(entity);
                            }
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public DealResponse createDeal(DealRequest dealRequest) {
        DealResponse response = mapper.fromEntityToResponse(dealRepository.save(mapper.fromRequestToEntity(dealRequest)));
        return response;
    }

    @Override
    public DealResponse updateById(DealUpdateRequest dealRequest) {
        return mapper.fromEntityToResponse(dealRepository.updateById(mapper.fromUpdateRequestToEntity(dealRequest)));
    }

    @Override
    public DealResponse findById(UUID dealId) {
        return mapper.fromEntityToResponse(dealRepository.findById(dealId));
    }

    @Override
    public void setStatus(UUID dealId, String status) {
        dealRepository.setStatus(dealId, status);
        if (Dealstatus.valueOf(status).equals(Dealstatus.THWARTED)) {
            DealEntity dealEntity = dealRepository.findById(dealId);
            realtyRepository.transferRealtyToAgent(dealEntity.getRealtyId(), dealEntity.getClientId());
        } else if (Dealstatus.valueOf(status).equals(Dealstatus.COMPLETED)) {
            DealEntity dealEntity = dealRepository.findById(dealId);
            realtyRepository.setStatus(dealEntity.getRealtyId(), "DONE");
        }
    }
}
