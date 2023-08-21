package com.example.advertismentService.controllers;

import api.RealtyHouseApi;
import com.example.advertismentService.exception.realty.flat.NotYourFlatException;
import com.example.advertismentService.model.jooq.schema.enums.Dealstatus;
import com.example.advertismentService.security.utils.AuthorizationHeaderUtil;
import com.example.advertismentService.security.utils.JwtUtil;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.deal.DealRequest;
import dto.request.realty.IdAndRealtyStatusRequest;
import dto.request.realty.home.RealtyHomeRequest;
import dto.request.realty.home.RealtyHomeUpdateRequest;
import dto.response.realty.home.RealtyHomeResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.realty.house.NotYoursHouseException;
import com.example.advertismentService.service.DealService;
import com.example.advertismentService.service.RealtyHomeService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class RealtyHouseController implements RealtyHouseApi {

    private final RealtyHomeService realtyHomeService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final DealService dealService;


    private UUID getUUIDFromToken(HttpServletRequest request) {
        return UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId());
    }

    @Override
    public EntityPage getAllHomeForBuying(int page, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyHomeService.findAll(page, List.of("OPEN"), null,
                List.of("PERMANENT"), columnsAndOrder);
    }

    @Override
    public EntityPage getAllHomeForBuyingForAgent(HttpServletRequest request, int page, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyHomeService.findAllForAgent(page, List.of("SEARCH_AGENT"), null,
                List.of("PERMANENT"), columnsAndOrder, getUUIDFromToken(request));
    }

    @Override
    public EntityPage getAllHomeForRent(int page, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyHomeService.findAll(page, List.of("OPEN"), null,
                List.of("MONTH", "DAY", "YEAR"), columnsAndOrder);
    }

    @Override
    public EntityPage getAllHomeForRentForAgent(HttpServletRequest request, int page, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return realtyHomeService.findAllForAgent(page, List.of("SEARCH_AGENT"), null,
                List.of("MONTH", "DAY", "YEAR"), columnsAndOrder, getUUIDFromToken(request));
    }

    @Override
    public RealtyHomeResponse createNewHome(HttpServletRequest request, RealtyHomeRequest home) {
        if (getUUIDFromToken(request).equals(home.getOwnerId())) {
            if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("AGENCY")) {
                home.setOwnerType("AGENCY");
            } else if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("BUILDER")) {
                home.setOwnerType("BUILDER");
            } else {
                home.setOwnerType("CLIENT");
            }
            return realtyHomeService.createHome(home);
        } else {
            throw new NotYoursHouseException();
        }
    }

    @Override
    public RealtyHomeResponse updateHome(HttpServletRequest request, RealtyHomeUpdateRequest newHome) {
        if (getUUIDFromToken(request).equals(newHome.getOwnerId())) {
            return realtyHomeService.updateHome(newHome);
        } else {
            throw new NotYoursHouseException();
        }
    }

    @Override
    public RealtyHomeResponse getFlatById(IdRequest flatId) {
        return realtyHomeService.findById(flatId.getId());
    }

    @Override
    public void setStatusHomeById(HttpServletRequest request, IdAndRealtyStatusRequest newStatusForHome) {
        if (jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getRole().equals("ADMIN") ||
                getUUIDFromToken(request).equals(realtyHomeService.findById(newStatusForHome.getId()).getId())) {
            realtyHomeService.setStatusHome(newStatusForHome.getId(), newStatusForHome.getNewStatus());
        } else {
            throw new NotYoursHouseException();
        }
    }

    @Override
    public EntityPage getHomeOfOwnerForBuying(HttpServletRequest request, int page, Map<String, String> columnsAndOrder, UUID ownerId) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        if (getUUIDFromToken(request).equals(ownerId) || jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId().equals("ADMIN")) {
            return realtyHomeService.findAllOfSomeOwner(page, List.of("OPEN", "HIDDEN", "DRAFT", "DELETE", "DONE", "SEARCH_AGENT"), null,
                    List.of("PERMANENT"), columnsAndOrder, ownerId);
        } else {
            throw new NotYoursHouseException();
        }
    }

    @Override
    public EntityPage getHomeOfOwnerForRent(HttpServletRequest request, int page, Map<String, String> columnsAndOrder, UUID ownerId) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        if (getUUIDFromToken(request).equals(ownerId) || jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId().equals("ADMIN")) {
            return realtyHomeService.findAllOfSomeOwner(page, List.of("OPEN", "HIDDEN", "DRAFT", "DELETE", "DONE", "SEARCH_AGENT"), null,
                    List.of("MONTH", "DAY", "YEAR"), columnsAndOrder, ownerId);
        } else {
            throw new NotYoursHouseException();
        }
    }

    @Override
    public void interestedInSomeRealty(HttpServletRequest request, UUID realtyId) {
        realtyHomeService.interestedInRealty(getUUIDFromToken(request), realtyId);
    }

    @Override
    public void transferRealtyToAgent(HttpServletRequest request, UUID realtyId, UUID agentId) {
        if (realtyHomeService.findById(realtyId).getOwnerId().equals(getUUIDFromToken(request))) {
            realtyHomeService.transferRealtyToAgent(realtyId, agentId);
            dealService.createDeal(DealRequest.builder()
                    .realtyId(realtyId)
                    .brokerId(agentId)
                    .clientId(getUUIDFromToken(request))
                    .status(Dealstatus.IN_PROCESS.toString())
                    .build());
        } else {
            throw new NotYourFlatException();
        }
    }


}
