package com.example.authenticationService.controller;

import com.example.authenticationService.exception.ChangeForeignAccount;
import com.example.authenticationService.exception.agent.AgentAlreadyWork;
import com.example.authenticationService.model.jooq.schema.enums.Agentlevel;
import com.example.authenticationService.security.entity.ParsedToken;
import com.example.authenticationService.security.utils.AuthorizationHeaderUtil;
import com.example.authenticationService.security.utils.JwtUtil;
import com.example.authenticationService.service.AgentService;
import com.example.authenticationService.service.OrganisationService;
import api.OrganisationApi;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.cianuser.organisation.OrganisationRequest;
import dto.request.cianuser.organisation.OrganisationUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.agent.AgentResponse;
import dto.response.cianuser.organisation.OrganisationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrganisationController implements OrganisationApi {

    private final OrganisationService organisationService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final AgentService agentService;

    private ParsedToken getParsedToken(HttpServletRequest request) {
        return jwtUtil.parse(authorizationHeaderUtil.getToken(request));
    }


    @Override
    public EntityPage getAllUsers(int page, Map<String, String> columnsAndOrder, List<String> status) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        if (status.isEmpty()) {
            status = List.of("REGISTERED", "VERIFIED", "DELETE", "BANNED");
        }
        return organisationService.findAll(page, columnsAndOrder, status);
    }

    @Override
    public OrganisationResponse createNewOrganisation(OrganisationRequest organisationRequest) {
        return organisationService.createNewOrganisation(organisationRequest);
    }

    @Override
    public CianUserResponse updateOrganisationById(HttpServletRequest request, OrganisationUpdateRequest organisationUpdateRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (parsedToken.getId().equals(organisationUpdateRequest.getId().toString())) {
            return organisationService.updateOrganisation(organisationUpdateRequest);
        } else {
            throw new ChangeForeignAccount();
        }
    }

    @Override
    public void deleteOrganisationById(HttpServletRequest request, IdRequest idRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (parsedToken.getId().equals(idRequest.getId().toString())) {
            organisationService.deleteOrganisationById(idRequest.getId());
        } else {
            throw new ChangeForeignAccount();
        }
    }

    @Override
    public OrganisationResponse getOrganisationById(IdRequest idRequest) {
        return organisationService.getOrganisationById(idRequest.getId());
    }

    @Override
    public void hireWorker(HttpServletRequest request, IdRequest idRequest) {
        AgentResponse agentResponse = agentService.getAgentById(idRequest.getId());
        if (!agentResponse.getId().equals(agentResponse.getAgencyId())) {
            throw new AgentAlreadyWork();
        } else {
            UUID organisationId = organisationService.getOrganisationById(UUID.fromString(getParsedToken(request).getId())).getOrganisationId();
            agentService.hireAgent(idRequest.getId(), organisationId);
        }
    }

    @Override
    public void dismissWorker(HttpServletRequest request, IdRequest idRequest) {
        UUID organisationId = organisationService.getOrganisationById(UUID.fromString(getParsedToken(request).getId())).getOrganisationId();
        if (organisationId.equals(agentService.getAgentById(idRequest.getId()).getAgencyId())) {
            agentService.dismissAgent(idRequest.getId());
        }
    }

    @Override
    public void newAgentLevel(UUID agentId, String agentLevel) {
        agentService.setNewLevel(agentId, Agentlevel.valueOf(agentLevel));
    }
}
