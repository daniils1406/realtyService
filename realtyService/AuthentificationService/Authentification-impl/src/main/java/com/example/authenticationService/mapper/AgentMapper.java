package com.example.authenticationService.mapper;

import com.example.authenticationService.model.jooq.schema.tables.pojos.AgentEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import dto.request.cianuser.agent.AgentRequest;
import dto.request.cianuser.agent.AgentUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.agent.AgentAdminResponse;
import dto.response.cianuser.agent.AgentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "experience", ignore = true)
    AgentEntity fromRequestToEntity(AgentRequest request);


    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userType", ignore = true)
    @Mapping(target = "experience", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "login", ignore = true)
    @Mapping(target = "agencyId", ignore = true)
    AgentEntity fromUpdateRequestToEntity(AgentUpdateRequest request);


    AgentResponse fromEntityToResponse(AgentEntity agentEntity);

    CianUserResponse fromEntityToResponseUpdate(CianUserEntity userEntity);

    AgentAdminResponse fromEntityToAdminResponse(AgentEntity agentEntity);
}
