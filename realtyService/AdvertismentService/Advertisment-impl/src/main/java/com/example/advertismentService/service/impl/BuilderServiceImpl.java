package com.example.advertismentService.service.impl;

import com.example.advertismentService.mapper.BuilderMapper;
import com.example.advertismentService.model.jooq.schema.tables.pojos.BuilderEntity;
import dto.EntityPage;
import dto.request.builder.BuilderRequest;
import dto.request.builder.BuilderUpdateRequest;
import dto.response.builder.BuilderAdminWithComplexesResponse;
import dto.response.builder.BuilderResponse;
import dto.response.builder.BuilderWithComplexesResponse;
import dto.response.residentialcomplex.ResidentialComplexResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.builder.BuilderAlreadyExistsException;
import com.example.advertismentService.repository.BuilderRepository;
import com.example.advertismentService.repository.ResidentialComplexRepository;
import com.example.advertismentService.service.BuilderService;
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
public class BuilderServiceImpl implements BuilderService {

    private final BuilderMapper mapper;

    private final BuilderRepository builderRepository;

    private final ResidentialComplexRepository residentialComplexRepository;

    @Override
    public EntityPage findAll(int page, List<String> status, Map<String, String> columnsAndOrder, boolean isAdmin) {

        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<BuilderEntity> builderPage = builderRepository.findAll(pageRequest, status);
        return EntityPage.builder()
                .totalPages(builderPage.getTotalPages())
                .data(builderPage.getContent().stream()
                        .map(entity -> {
                            if (isAdmin) {
                                return mapper.toAdminResponse(entity);
                            } else {
                                return mapper.toResponse(entity);
                            }
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public BuilderResponse createBuilder(BuilderRequest builderData) {
        if (builderRepository.findByName(builderData.getName()) == null) {
            return mapper.toResponse(builderRepository.save(mapper.fromRequestToEntity(builderData)));
        } else {
            throw new BuilderAlreadyExistsException(builderData.getName());
        }
    }

    @Override
    public BuilderResponse updateBuilderById(BuilderUpdateRequest newBuilder) {
        BuilderEntity entity = builderRepository.findByName(newBuilder.getName());
        if (entity == null || entity.getId().equals(newBuilder.getId())) {
            return mapper.toResponse(builderRepository.updateById(mapper.fromUpdateRequestToEntity(newBuilder)));
        } else {
            throw new BuilderAlreadyExistsException(newBuilder.getName());
        }
    }

    @Override
    public BuilderWithComplexesResponse findById(UUID builderId) {
        BuilderWithComplexesResponse builderWithComplexesResponse = mapper
                .toFullResponse(builderRepository.findById(builderId));
        builderWithComplexesResponse.setResidentialComplexesOfBuilder(
                residentialComplexRepository.findResidentialComplexesOfSomeBuilder(builderId));
        return builderWithComplexesResponse;
    }

    @Override
    public void deleteById(UUID builderId) {
        builderRepository.findById(builderId);
        builderRepository.deleteById(builderId);
    }

    @Override
    public void approveById(UUID builderId) {
        builderRepository.findById(builderId);
        builderRepository.verifyById(builderId);
    }

    @Override
    public BuilderAdminWithComplexesResponse findByIdByAdmin(UUID builderId) {
        BuilderAdminWithComplexesResponse builderWithComplexesResponse = mapper
                .toFullAdminResponse(builderRepository.findById(builderId));
        List<ResidentialComplexResponse> residentialComplexes =
                residentialComplexRepository.findResidentialComplexesOfSomeBuilder(builderId);
        residentialComplexes = residentialComplexes.stream().map(complex -> {
            complex.setBuilderName(builderWithComplexesResponse.getName());
            return complex;
        }).collect(Collectors.toList());
        builderWithComplexesResponse.setResidentialComplexesOfBuilder(
                residentialComplexes);
        return builderWithComplexesResponse;
    }


}
