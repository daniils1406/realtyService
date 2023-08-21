package com.example.advertismentService.controllers;

import api.ResidentialComplexApi;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.residentialcomplex.ResidentialComplexRequest;
import dto.request.residentialcomplex.ResidentialComplexUpdateRequest;
import dto.response.residentialcomplex.ResidentialComplexAdminResponse;
import dto.response.residentialcomplex.ResidentialComplexResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.service.ResidentialComplexService;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class ResidentialComplexController implements ResidentialComplexApi {

    private final ResidentialComplexService residentialComplexService;


    @Override
    public EntityPage getAllResidentialComplex(int page, Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return residentialComplexService.findAll(page, List.of("VERIFIED"), columnsAndOrder, false);
    }

    @Override
    public ResidentialComplexResponse createNewResidentialComplex(ResidentialComplexRequest residentialComplexRequest) {
        return residentialComplexService.createResidentialComplex(residentialComplexRequest);
    }

    @Override
    public ResidentialComplexResponse updateResidentialComplexById(
            ResidentialComplexUpdateRequest residentialComplexRequest) {
        return residentialComplexService.updateResidentialComplexById(residentialComplexRequest);
    }

    @Override
    public ResidentialComplexResponse getResidentialComplexById(IdRequest residentialComplexId) {
        return residentialComplexService.findById(residentialComplexId.getId());
    }

    @Override
    public void deleteResidentialComplexById(IdRequest residentialComplexId) {
        residentialComplexService.deleteById(residentialComplexId.getId());
    }

    @Override
    public void approveResidentialComplexById(IdRequest residentialComplexId) {
        residentialComplexService.approveById(residentialComplexId.getId());
    }

    @Override
    public EntityPage getAllResidentialComplexByAdmin(int page, List<String> status,
                                                      Map<String, String> columnsAndOrder) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        return residentialComplexService.findAll(page, status, columnsAndOrder, true);
    }

    @Override
    public ResidentialComplexAdminResponse getResidentialComplexByIdBuAdmin(IdRequest residentialComplexId) {
        return residentialComplexService.findByIdByAdmin(residentialComplexId.getId());
    }
}