package com.example.advertismentService.mapper;

import com.example.advertismentService.model.jooq.schema.tables.pojos.HouseEntity;
import dto.request.realty.home.RealtyHomeRequest;
import dto.request.realty.home.RealtyHomeUpdateRequest;
import dto.response.realty.home.RealtyHomeAdminResponse;
import dto.response.realty.home.RealtyHomeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RealtyHomeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    HouseEntity fromRequestToEntity(RealtyHomeRequest realtyHomeRequest);

    @Mapping(target = "insertDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "ownerType", ignore = true)
    HouseEntity fromUpdateRequestToEntity(RealtyHomeUpdateRequest realtyHomeUpdateRequest);

    @Mapping(target = "ownerName", ignore = true)
    @Mapping(target = "ownerLastName", ignore = true)
    @Mapping(target = "ownerLevel", ignore = true)
    RealtyHomeResponse fromEntityToResponse(HouseEntity realtyEntity);


    @Mapping(target = "ownerName", ignore = true)
    @Mapping(target = "ownerLastName", ignore = true)
    @Mapping(target = "ownerLevel", ignore = true)
    RealtyHomeAdminResponse fromEntityToAdminResponse(HouseEntity realtyEntity);

}