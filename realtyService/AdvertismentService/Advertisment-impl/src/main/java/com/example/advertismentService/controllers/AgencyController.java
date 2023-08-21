package com.example.advertismentService.controllers;

import api.AgencyApi;
import com.example.advertismentService.exception.agency.NotYourAgencyException;
import com.example.advertismentService.grpc.GrpcAuthenticationServiceImpl;
import com.example.advertismentService.security.entity.ParsedToken;
import com.example.advertismentService.security.utils.AuthorizationHeaderUtil;
import com.example.advertismentService.security.utils.JwtUtil;
import com.example.advertismentService.service.AgencyService;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.agency.AgencyRequest;
import dto.request.agency.AgencyUpdateRequest;
import dto.response.agency.AgencyAdminResponse;
import dto.response.agency.AgencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class AgencyController implements AgencyApi {

    private final AgencyService agencyService;
    private final JwtUtil jwtUtil;
    private final AuthorizationHeaderUtil authorizationHeaderUtil;
    private final GrpcAuthenticationServiceImpl grpcAuthenticationService;


    private ParsedToken getParsedToken(HttpServletRequest request) {
        return jwtUtil.parse(authorizationHeaderUtil.getToken(request));
    }

    @Override
    public EntityPage getAllAgency(int page, List<String> level, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return agencyService.findAll(page, List.of("VERIFIED"), level, columnsAndOrder, false);
    }

    @Override
    public AgencyResponse createNewAgency(HttpServletRequest request, AgencyRequest agencyRequest) {
        return agencyService.createAgency(agencyRequest);
    }

    @Override
    public AgencyResponse updateAgencyById(HttpServletRequest request, AgencyUpdateRequest agencyRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (agencyRequest.getId().equals(grpcAuthenticationService.findCompanyWhichPersonBelong(UUID.fromString(parsedToken.getId())))) {
            return agencyService.updateAgencyById(agencyRequest);
        } else {
            throw new NotYourAgencyException();
        }
    }

    @Override
    public AgencyResponse getAgencyById(IdRequest agencyId) {
        return agencyService.findById(agencyId.getId());
    }

    @Override
    public void deleteAgencyById(HttpServletRequest request, IdRequest agencyId) {
        ParsedToken parsedToken = getParsedToken(request);
        if (agencyId.getId().equals(grpcAuthenticationService.findCompanyWhichPersonBelong(UUID.fromString(parsedToken.getId())))) {
            agencyService.deleteById(agencyId.getId());
        } else {
            throw new NotYourAgencyException();
        }
    }

    @Override
    public void approveAgencyById(IdRequest agencyId) {
        agencyService.approveById(agencyId.getId());
    }

    @Override
    public void updateLevelById(IdRequest agencyId, String newLevel) {
        agencyService.updateLevel(agencyId.getId(), newLevel);
    }

    @Override
    public EntityPage getAllAgencyByAdmin(int page, List<String> status, List<String> level,
                                          Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return agencyService.findAll(page, status, level, columnsAndOrder, true);
    }

    @Override
    public AgencyAdminResponse getAgencyByIdByAdmin(IdRequest agencyId) {
        return agencyService.findByIdByAdmin(agencyId.getId());
    }
}
