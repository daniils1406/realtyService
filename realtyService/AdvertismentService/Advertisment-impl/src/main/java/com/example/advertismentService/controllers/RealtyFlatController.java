package com.example.advertismentService.controllers;

import api.RealtyFlatApi;
import com.example.advertismentService.exception.realty.flat.NotYourFlatException;
import com.example.advertismentService.model.jooq.schema.enums.Dealstatus;
import com.example.advertismentService.security.utils.AuthorizationHeaderUtil;
import com.example.advertismentService.security.utils.JwtUtil;
import com.example.advertismentService.service.RealtyFlatService;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.deal.DealRequest;
import dto.request.realty.IdAndRealtyStatusRequest;
import dto.request.realty.flat.RealtyFlatRequest;
import dto.request.realty.flat.RealtyFlatUpdateRequest;
import dto.response.realty.flat.RealtyFlatResponse;
import lombok.RequiredArgsConstructor;

import com.example.advertismentService.service.DealService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RealtyFlatController implements RealtyFlatApi {

    private final RealtyFlatService realtyFlatService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final DealService dealService;


    private UUID getUUIDFromToken(HttpServletRequest request) {
        return UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId());
    }

    @Override
    public EntityPage getAllFlatForBuying(int page, Map<String, String> columnsAndOrder, String realtyType) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyFlatService.findAll(page, List.of("OPEN"), realtyType, List.of("PERMANENT"), columnsAndOrder);
    }

    @Override
    public EntityPage getAllFlatForBuyingForAgent(HttpServletRequest request, int page, Map<String, String> columnsAndOrder, String realtyType) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyFlatService.findAllForAgent(page, List.of("SEARCH_AGENT"), realtyType, List.of("PERMANENT"), columnsAndOrder, getUUIDFromToken(request));
    }

    @Override
    public EntityPage getAllFlatForRent(int page, Map<String, String> columnsAndOrder, String realtyType) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyFlatService.findAll(page, List.of("OPEN"), realtyType,
                List.of("MONTH", "DAY", "YEAR"), columnsAndOrder);
    }

    @Override
    public EntityPage getAllFlatForRentForAgent(HttpServletRequest request, int page, Map<String, String> columnsAndOrder, String realtyType) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyFlatService.findAllForAgent(page, List.of("SEARCH_AGENT"), realtyType,
                List.of("MONTH", "DAY", "YEAR"), columnsAndOrder, getUUIDFromToken(request));
    }

    @Override
    public RealtyFlatResponse createNewFlat(HttpServletRequest request, RealtyFlatRequest flat) {
        if (getUUIDFromToken(request).equals(flat.getOwnerId())) {
            if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("AGENCY")) {
                flat.setOwnerType("AGENCY");
            } else if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("BUILDER")) {
                flat.setOwnerType("BUILDER");
            } else {
                flat.setOwnerType("CLIENT");
            }
            return realtyFlatService.createFlat(flat);
        } else {
            throw new NotYourFlatException();
        }
    }

    @Override
    public RealtyFlatResponse updateFlat(HttpServletRequest request, RealtyFlatUpdateRequest newFlat) {
        if (getUUIDFromToken(request).equals(newFlat.getOwnerId())) {
            return realtyFlatService.updateFlat(newFlat);
        } else {
            throw new NotYourFlatException();
        }
    }

    @Override
    public RealtyFlatResponse getFlatById(IdRequest flatId) {
        return realtyFlatService.findById(flatId.getId());
    }

    @Override
    public void setStatusFlatById(HttpServletRequest request, IdAndRealtyStatusRequest newStatusForFlat) {

        if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("ADMIN") ||
                getUUIDFromToken(request).equals(realtyFlatService.findById(newStatusForFlat.getId()).getId())) {
            realtyFlatService.setStatusFlat(newStatusForFlat.getId(), newStatusForFlat.getNewStatus());
        } else {
            throw new NotYourFlatException();
        }
    }

    @Override
    public EntityPage getFlatOfOwnerBuy(HttpServletRequest request, int page, Map<String, String> columnsAndOrder, String realtyType, UUID ownerId) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        if (getUUIDFromToken(request).equals(ownerId) || jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("ADMIN")) {
            return realtyFlatService.findAllOfSomeOwner(page, List.of("OPEN", "HIDDEN", "DRAFT", "DELETE", "DONE", "SEARCH_AGENT"), realtyType, List.of("PERMANENT"), columnsAndOrder, ownerId);
        } else {
            throw new NotYourFlatException();
        }
    }

    @Override
    public EntityPage getFlatOfOwnerRent(HttpServletRequest request, int page, Map<String, String> columnsAndOrder, String realtyType, UUID ownerId) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        if (getUUIDFromToken(request).equals(ownerId) || jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("ADMIN")) {
            return realtyFlatService.findAllOfSomeOwner(page, List.of("OPEN", "HIDDEN", "DRAFT", "DELETE", "DONE", "SEARCH_AGENT"), realtyType,
                    List.of("MONTH", "DAY", "YEAR"), columnsAndOrder, ownerId);
        } else {
            throw new NotYourFlatException();
        }
    }

    @Override
    public void interestedInSomeRealty(HttpServletRequest request, UUID realtyId) {
        realtyFlatService.interestedInRealty(getUUIDFromToken(request), realtyId);
    }

    @Override
    public void transferRealtyToAgent(HttpServletRequest request, UUID realtyId, UUID agentId) {
        if (realtyFlatService.findById(realtyId).getOwnerId().equals(getUUIDFromToken(request))) {
            realtyFlatService.transferRealtyToAgent(realtyId, agentId);
            dealService.createDeal(DealRequest.builder()
                    .realtyId(realtyId)
                    .brokerId(agentId)
                    .clientId(getUUIDFromToken(request))
                    .insertDate(Date.valueOf(LocalDate.now()))
                    .status(Dealstatus.IN_PROCESS.toString())
                    .build());
        } else {
            throw new NotYourFlatException();
        }
    }

}
