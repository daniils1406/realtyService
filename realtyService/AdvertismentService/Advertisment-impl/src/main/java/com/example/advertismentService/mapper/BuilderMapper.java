package com.example.advertismentService.mapper;


import com.example.advertismentService.model.jooq.schema.tables.pojos.BuilderEntity;
import dto.request.builder.BuilderRequest;
import dto.request.builder.BuilderUpdateRequest;
import dto.response.builder.BuilderAdminResponse;
import dto.response.builder.BuilderAdminWithComplexesResponse;
import dto.response.builder.BuilderResponse;
import dto.response.builder.BuilderWithComplexesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BuilderMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "insertDate", ignore = true)
    BuilderEntity fromRequestToEntity(BuilderRequest builderRequest);


    @Mapping(target = "status", ignore = true)
    @Mapping(target = "foundationYear", ignore = true)
    @Mapping(target = "insertDate", ignore = true)
    BuilderEntity fromUpdateRequestToEntity(BuilderUpdateRequest builderRequest);


    BuilderResponse toResponse(BuilderEntity builderEntity);

    @Mapping(target = "residentialComplexesOfBuilder", ignore = true)
    BuilderWithComplexesResponse toFullResponse(BuilderEntity builderEntity);

    BuilderAdminResponse toAdminResponse(BuilderEntity builderEntity);

    @Mapping(target = "residentialComplexesOfBuilder", ignore = true)
    BuilderAdminWithComplexesResponse toFullAdminResponse(BuilderEntity builderEntity);

}
