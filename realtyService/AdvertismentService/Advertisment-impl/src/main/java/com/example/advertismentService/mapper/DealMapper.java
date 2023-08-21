package com.example.advertismentService.mapper;

import com.example.advertismentService.model.jooq.schema.tables.pojos.DealEntity;
import dto.request.deal.DealRequest;
import dto.request.deal.DealUpdateRequest;
import dto.response.deal.DealResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DealMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertDate", ignore = true)
    DealEntity fromRequestToEntity(DealRequest dealRequest);


    @Mapping(target = "insertDate", ignore = true)
    DealEntity fromUpdateRequestToEntity(DealUpdateRequest dealUpdateRequest);


    DealResponse fromEntityToResponse(DealEntity entity);
}
