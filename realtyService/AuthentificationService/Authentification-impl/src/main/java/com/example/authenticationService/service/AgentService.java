package com.example.authenticationService.service;

import com.example.authenticationService.model.jooq.schema.enums.Agentlevel;
import dto.EntityPage;
import dto.request.cianuser.agent.AgentRequest;
import dto.request.cianuser.agent.AgentUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.agent.AgentAdminResponse;
import dto.response.cianuser.agent.AgentResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AgentService {
    EntityPage findAll(int page, Map<String, String> columnsAndOrder, List<String> experience, List<String> status);

    AgentResponse createNewAgent(AgentRequest agentRequest);

    CianUserResponse updateAgentById(AgentUpdateRequest updateRequest);

    void deleteById(UUID id);

    AgentResponse getAgentById(UUID id);

    void hireAgent(UUID agentId, UUID organisationId);

    void dismissAgent(UUID agentId);

    void setNewLevel(UUID agentId, Agentlevel agentlevel);

    List<AgentAdminResponse> getAgentsOfAgency(UUID id);
}
