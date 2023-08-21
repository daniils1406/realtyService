package com.example.authenticationService.mapper;

import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CianUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userType", ignore = true)
    CianUserEntity fromRequestToEntity(CianUserRequest cianUserRequest);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "login", ignore = true)
    CianUserEntity fromUpdateRequestToEntity(CianUserUpdateRequest cianUserRequest);

    CianUserResponse fromEntityToResponse(CianUserEntity entity);
}
