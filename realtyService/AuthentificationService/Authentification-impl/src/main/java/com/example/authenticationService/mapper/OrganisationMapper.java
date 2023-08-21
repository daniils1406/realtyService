package com.example.authenticationService.mapper;

import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.OrganisationEntity;
import dto.request.cianuser.organisation.OrganisationRequest;
import dto.request.cianuser.organisation.OrganisationUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.organisation.OrganisationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganisationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userType", ignore = true)
    OrganisationEntity fromRequestToEntity(OrganisationRequest organisationRequest);


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "login", ignore = true)
    @Mapping(target = "organisationId", ignore = true)
    OrganisationEntity fromUpdateRequestToEntity(OrganisationUpdateRequest organisationRequest);


    OrganisationResponse fromEntityToResponse(OrganisationEntity organisationEntity);

    CianUserResponse fromEntityToResponseUpdate(CianUserEntity userEntity);
}
