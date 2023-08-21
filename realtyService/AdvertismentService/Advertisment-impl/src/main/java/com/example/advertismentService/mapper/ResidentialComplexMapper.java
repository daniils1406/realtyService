package com.example.advertismentService.mapper;

import com.example.advertismentService.model.jooq.schema.tables.pojos.ResidentialComplexEntity;
import dto.request.residentialcomplex.ResidentialComplexRequest;
import dto.request.residentialcomplex.ResidentialComplexUpdateRequest;
import dto.response.residentialcomplex.ResidentialComplexAdminResponse;
import dto.response.residentialcomplex.ResidentialComplexResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResidentialComplexMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "region", ignore = true)
    ResidentialComplexEntity fromRequestToEntity(ResidentialComplexRequest request);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "region", ignore = true)
    ResidentialComplexEntity fromUpdateRequestToEntity(ResidentialComplexUpdateRequest request);

    @Mapping(target = "builderName", ignore = true)
    @Mapping(target = "region", ignore = true)
    ResidentialComplexResponse toResponse(ResidentialComplexEntity entity);

    @Mapping(target = "builderName", ignore = true)
    @Mapping(target = "region", ignore = true)
    ResidentialComplexAdminResponse toAdminResponse(ResidentialComplexEntity entity);

}
