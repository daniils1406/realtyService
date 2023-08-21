package com.example.advertismentService.service.impl;

import com.example.advertismentService.exception.agency.AgencyAlreadyExistsException;
import com.example.advertismentService.mapper.AgencyMapper;
import com.example.advertismentService.model.jooq.schema.tables.pojos.AgencyEntity;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsAndAgencyEntity;
import com.example.advertismentService.service.AgencyService;
import dto.EntityPage;
import dto.request.agency.AgencyRequest;
import dto.request.agency.AgencyUpdateRequest;
import dto.response.agency.AgencyAdminResponse;
import dto.response.agency.AgencyResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.repository.AgencyRepository;
import com.example.advertismentService.repository.RegionAndAgencyRepository;
import com.example.advertismentService.repository.RegionRepository;
import com.example.advertismentService.util.PageRequestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    private final RegionRepository regionRepository;

    private final RegionAndAgencyRepository regionAndAgencyRepository;

    private final AgencyMapper mapper;


    @Override
    public EntityPage findAll(int page, List<String> status, List<String> level, Map<String, String> columnsAndOrder,
                              boolean isAdmin) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<AgencyEntity> agencyPage = agencyRepository.findAll(pageRequest, status, level);
        return EntityPage.builder()
                .totalPages(agencyPage.getTotalPages())
                .data(agencyPage.getContent().stream()
                        .map(entity -> {
                            List<Integer> regionsCode = regionAndAgencyRepository.findAllRegionsOfAgency(
                                    entity.getId());
                            List<String> regions = new LinkedList<>();
                            for (Integer regionCode : regionsCode) {
                                regions.add(regionRepository.findByCode(regionCode).getRegionName());
                            }
                            if (isAdmin) {
                                AgencyAdminResponse response = mapper.toAdminResponse(entity);
                                response.setRegions(regions);
                                return response;
                            } else {
                                AgencyResponse response = mapper.toResponse(entity);
                                response.setRegions(regions);
                                return response;
                            }
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public AgencyResponse createAgency(AgencyRequest agencyRequest) {
        if (agencyRepository.findByName(agencyRequest.getName()) == null) {
            AgencyResponse response = mapper.toResponse(agencyRepository.save(
                    mapper.fromRequestToEntity(agencyRequest)));
            List<String> regions = new LinkedList<>();
            for (String regionName : agencyRequest.getRegions()) {
                RegionsAndAgencyEntity entity = regionAndAgencyRepository.addRegionToAgency(response.getId(),
                        regionRepository.findByName(regionName).getCode());
                regions.add(regionRepository.findByCode(entity.getRegionCode()).getRegionName());
            }
            response.setRegions(regions);
            return response;
        } else {
            throw new AgencyAlreadyExistsException(agencyRequest.getName());
        }
    }

    @Override
    public AgencyResponse updateAgencyById(AgencyUpdateRequest agencyRequest) {
        AgencyEntity entity = agencyRepository.findByName(agencyRequest.getName());
        if (entity == null || entity.getId().equals(agencyRequest.getId())) {
            AgencyResponse response = mapper.toResponse(agencyRepository.updateById(
                    mapper.fromUpdateRequestToEntity(agencyRequest)));
            List<Integer> oldRegions = regionAndAgencyRepository.findAllRegionsOfAgency(agencyRequest.getId());
            List<Integer> newRegions = agencyRequest.getRegions().stream().map(regionName ->
                    regionRepository.findByName(regionName).getCode()).collect(Collectors.toList());
            for (Integer oldRegion : oldRegions) {
                if (!newRegions.contains(oldRegion)) {
                    regionAndAgencyRepository.deleteRegionOfAgency(agencyRequest.getId(), oldRegion);
                }
            }
            for (Integer newRegion : newRegions) {
                if (!oldRegions.contains(newRegion)) {
                    regionAndAgencyRepository.addRegionToAgency(agencyRequest.getId(), newRegion);
                }
            }

            response.setRegions(agencyRequest.getRegions());
            return response;
        } else {
            throw new AgencyAlreadyExistsException(agencyRequest.getName());
        }
    }

    @Override
    public AgencyResponse findById(UUID agencyId) {
        AgencyResponse response = mapper.toResponse(agencyRepository.findById(agencyId));
        response.setRegions(findRegionsOfAgency(agencyId));
        return response;
    }

    @Override
    public void deleteById(UUID agencyId) {
        agencyRepository.deleteById(agencyId);
    }

    @Override
    public void approveById(UUID agencyId) {
        agencyRepository.approveById(agencyId);
    }

    @Override
    public void updateLevel(UUID agencyId, String newLevel) {
        agencyRepository.updateLevelById(agencyId, newLevel);
    }

    @Override
    public AgencyAdminResponse findByIdByAdmin(UUID agencyId) {
        AgencyAdminResponse response = mapper.toAdminResponse(agencyRepository.findById(agencyId));
        response.setRegions(findRegionsOfAgency(agencyId));
        return response;
    }

    public List<String> findRegionsOfAgency(UUID agencyId) {
        List<Integer> regionsCode = regionAndAgencyRepository.findAllRegionsOfAgency(agencyId);
        List<String> regionsName = new LinkedList<>();
        for (Integer code : regionsCode) {
            regionsName.add(regionRepository.findByCode(code).getRegionName());
        }
        return regionsName;
    }
}
