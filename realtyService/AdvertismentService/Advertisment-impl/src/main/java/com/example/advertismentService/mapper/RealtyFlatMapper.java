package com.example.advertismentService.mapper;

import com.example.advertismentService.model.jooq.schema.tables.pojos.FlatEntity;
import dto.request.realty.flat.RealtyFlatRequest;
import dto.request.realty.flat.RealtyFlatUpdateRequest;
import dto.response.realty.flat.RealtyFlatAdminResponse;
import dto.response.realty.flat.RealtyFlatResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RealtyFlatMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    FlatEntity fromRequestToEntity(RealtyFlatRequest realtyFlatRequest);


    @Mapping(target = "insertDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "ownerType", ignore = true)
    FlatEntity fromUpdateRequestToEntity(RealtyFlatUpdateRequest realtyFlatRequest);

    @Mapping(target = "ownerName", ignore = true)
    @Mapping(target = "ownerLastName", ignore = true)
    @Mapping(target = "ownerLevel", ignore = true)
    @Mapping(target = "residentialComplexName", ignore = true)
    RealtyFlatResponse fromEntityToResponse(FlatEntity realty);

    @Mapping(target = "ownerName", ignore = true)
    @Mapping(target = "ownerLastName", ignore = true)
    @Mapping(target = "ownerLevel", ignore = true)
    @Mapping(target = "residentialComplexName", ignore = true)
    RealtyFlatAdminResponse fromEntityToAdminResponse(FlatEntity realty);
}
