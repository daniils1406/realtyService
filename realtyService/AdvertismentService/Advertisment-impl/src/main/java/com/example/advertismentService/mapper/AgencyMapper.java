package com.example.advertismentService.mapper;

import com.example.advertismentService.model.jooq.schema.tables.pojos.AgencyEntity;
import dto.request.agency.AgencyRequest;
import dto.request.agency.AgencyUpdateRequest;
import dto.response.agency.AgencyAdminResponse;
import dto.response.agency.AgencyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgencyMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertDate", ignore = true)
    @Mapping(target = "level", ignore = true)
    @Mapping(target = "status", ignore = true)
    AgencyEntity fromRequestToEntity(AgencyRequest request);


    @Mapping(target = "insertDate", ignore = true)
    @Mapping(target = "level", ignore = true)
    @Mapping(target = "status", ignore = true)
    AgencyEntity fromUpdateRequestToEntity(AgencyUpdateRequest request);

    @Mapping(target = "regions", ignore = true)
    AgencyResponse toResponse(AgencyEntity entity);

    @Mapping(target = "regions", ignore = true)
    AgencyAdminResponse toAdminResponse(AgencyEntity entity);
}
