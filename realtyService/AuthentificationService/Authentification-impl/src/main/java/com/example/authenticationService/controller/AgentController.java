package com.example.authenticationService.controller;

import com.example.authenticationService.exception.ChangeForeignAccount;
import com.example.authenticationService.security.entity.ParsedToken;
import com.example.authenticationService.security.utils.AuthorizationHeaderUtil;
import com.example.authenticationService.security.utils.JwtUtil;
import com.example.authenticationService.service.AgentService;
import api.AgentApi;
import dto.EntityPage;
import dto.request.IdRequest;
import dto.request.cianuser.agent.AgentRequest;
import dto.request.cianuser.agent.AgentUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.agent.AgentAdminResponse;
import dto.response.cianuser.agent.AgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AgentController implements AgentApi {

    private final AgentService agentService;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private ParsedToken getParsedToken(HttpServletRequest request) {
        return jwtUtil.parse(authorizationHeaderUtil.getToken(request));
    }

    @Override
    public EntityPage getAllUsers(int page, Map<String, String> columnsAndOrder, List<String> experience, List<String> status) {
        if (columnsAndOrder == null) {
            columnsAndOrder = new HashMap<>();
            columnsAndOrder.put("id", "asc");
        }
        if (experience.isEmpty()) {
            experience = List.of("STARTING", "VERIFIED", "EXPERIENCED", "PRO");
        }
        if (status.isEmpty()) {
            status = List.of("REGISTERED", "VERIFIED", "DELETE", "BANNED");
        }
        return agentService.findAll(page, columnsAndOrder, experience, status);
    }

    @Override
    public AgentResponse createNewAgent(AgentRequest agentRequest) {
        return agentService.createNewAgent(agentRequest);
    }

    @Override
    public CianUserResponse updateAgentById(HttpServletRequest request, AgentUpdateRequest updateRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (parsedToken.getId().equals(updateRequest.getId().toString())) {
            return agentService.updateAgentById(updateRequest);
        } else {
            throw new ChangeForeignAccount();
        }
    }

    @Override
    public void deleteAgentById(HttpServletRequest request, IdRequest idRequest) {
        ParsedToken parsedToken = getParsedToken(request);
        if (parsedToken.getId().equals(idRequest.getId().toString())) {
            agentService.deleteById(idRequest.getId());
        } else {
            throw new ChangeForeignAccount();
        }
    }

    @Override
    public AgentResponse getAgentById(IdRequest idRequest) {
        return agentService.getAgentById(idRequest.getId());
    }

    @Override
    public List<AgentAdminResponse> getAgentsOfAgency(IdRequest agencyId) {
        return agentService.getAgentsOfAgency(agencyId.getId());
    }

}
