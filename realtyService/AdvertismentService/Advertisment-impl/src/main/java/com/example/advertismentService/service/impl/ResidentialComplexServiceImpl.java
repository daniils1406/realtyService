package com.example.advertismentService.service.impl;

import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsEntity;
import com.example.advertismentService.model.jooq.schema.tables.pojos.ResidentialComplexEntity;
import dto.EntityPage;
import dto.request.residentialcomplex.ResidentialComplexRequest;
import dto.request.residentialcomplex.ResidentialComplexUpdateRequest;
import dto.response.residentialcomplex.ResidentialComplexAdminResponse;
import dto.response.residentialcomplex.ResidentialComplexResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.residentialcomplex.ResidentialComplexAlreadyExistsException;
import com.example.advertismentService.mapper.ResidentialComplexMapper;
import com.example.advertismentService.repository.BuilderRepository;
import com.example.advertismentService.repository.RealtyRepository;
import com.example.advertismentService.repository.RegionRepository;
import com.example.advertismentService.repository.ResidentialComplexRepository;
import com.example.advertismentService.service.ResidentialComplexService;
import com.example.advertismentService.util.PageRequestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ResidentialComplexServiceImpl implements ResidentialComplexService {

    private final ResidentialComplexRepository residentialComplexRepository;

    private final BuilderRepository builderRepository;

    private final RegionRepository regionRepository;

    private final ResidentialComplexMapper mapper;

    private final RealtyRepository realtyRepository;


    @Override
    public EntityPage findAll(int page, List<String> status, Map<String, String> columnsAndOrder, boolean isAdmin) {
        PageRequest pageRequest= PageRequestUtil.createPageRequest(page,columnsAndOrder);

        Page<ResidentialComplexEntity> residentialComplexPage = residentialComplexRepository.findAll(pageRequest, status);
        return EntityPage.builder()
                .totalPages(residentialComplexPage.getTotalPages())
                .data(residentialComplexPage.getContent().stream()
                        .map(entity -> {
                            if (isAdmin) {
                                ResidentialComplexAdminResponse response = mapper.toAdminResponse(entity);
                                response.setBuilderName(builderRepository.findById(response.getBuilderId()).getName());
                                response.setRegion(regionRepository.findByCode(entity.getRegion()).getRegionName());
                                return response;
                            } else {
                                ResidentialComplexResponse response = mapper.toResponse(entity);
                                response.setBuilderName(builderRepository.findById(response.getBuilderId()).getName());
                                response.setRegion(regionRepository.findByCode(entity.getRegion()).getRegionName());
                                return response;
                            }

                        })
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ResidentialComplexResponse createResidentialComplex(ResidentialComplexRequest residentialComplexRequest) {
        if (residentialComplexRepository.findByName(residentialComplexRequest.getName()) == null) {
            builderRepository.findById(residentialComplexRequest.getBuilderId());

            ResidentialComplexEntity entity = mapper.fromRequestToEntity(residentialComplexRequest);
            RegionsEntity regionsEntity = regionRepository.findByName(residentialComplexRequest.getRegion());
            entity.setRegion(regionsEntity.getCode());
            ResidentialComplexResponse response = mapper.toResponse(residentialComplexRepository.save(entity));
            response.setBuilderName(builderRepository.findById(residentialComplexRequest.getBuilderId()).getName());
            response.setRegion(regionsEntity.getRegionName());
            return response;
        } else {
            throw new ResidentialComplexAlreadyExistsException(residentialComplexRequest.getName());
        }
    }

    @Override
    public ResidentialComplexResponse updateResidentialComplexById(
            ResidentialComplexUpdateRequest residentialComplexUpdateRequest) {
        ResidentialComplexEntity residentialEntity =
                residentialComplexRepository.findByName(residentialComplexUpdateRequest.getName());
        if (residentialEntity == null || residentialEntity.getId().equals(residentialComplexUpdateRequest.getId())) {
            builderRepository.findById(residentialComplexUpdateRequest.getBuilderId());
            ResidentialComplexEntity entity = mapper.fromUpdateRequestToEntity(residentialComplexUpdateRequest);
            RegionsEntity region = regionRepository.findByName(residentialComplexUpdateRequest.getRegion());
            entity.setRegion(region.getCode());
            ResidentialComplexResponse response = mapper.toResponse(residentialComplexRepository.updateById(entity));
            response.setRegion(region.getRegionName());
            return response;
        } else {
            throw new ResidentialComplexAlreadyExistsException(residentialComplexUpdateRequest.getName());
        }
    }

    @Override
    public ResidentialComplexResponse findById(UUID residentialComplexId) {
        ResidentialComplexEntity entity = residentialComplexRepository.findById(residentialComplexId);
        ResidentialComplexResponse response = mapper.toResponse(entity);
        response.setBuilderName(builderRepository.findById(response.getBuilderId()).getName());
        response.setRegion(regionRepository.findByCode(entity.getRegion()).getRegionName());
        return response;
    }

    @Override
    public void deleteById(UUID residentialComplexId) {
        realtyRepository.bannedAllRealtyOfResidentialComplex(residentialComplexId);
        residentialComplexRepository.findById(residentialComplexId);
        residentialComplexRepository.deleteById(residentialComplexId);
    }

    @Override
    public void approveById(UUID residentialComplexId) {
        residentialComplexRepository.findById(residentialComplexId);
        residentialComplexRepository.approveById(residentialComplexId);
    }

    @Override
    public ResidentialComplexAdminResponse findByIdByAdmin(UUID residentialComplexId) {
        ResidentialComplexEntity entity = residentialComplexRepository.findById(residentialComplexId);
        ResidentialComplexAdminResponse response = mapper.toAdminResponse(entity);
        response.setBuilderName(builderRepository.findById(response.getBuilderId()).getName());
        response.setRegion(regionRepository.findByCode(entity.getRegion()).getRegionName());
        return response;
    }

}
