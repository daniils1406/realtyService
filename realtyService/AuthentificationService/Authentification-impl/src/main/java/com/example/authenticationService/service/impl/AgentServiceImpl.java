package com.example.authenticationService.service.impl;

import com.example.authenticationService.exception.agent.AgentAlreadyExistsException;
import com.example.authenticationService.exception.agent.ThisAgencyNotExist;
import com.example.authenticationService.grpc.GrpcAdvertisementServiceImpl;
import com.example.authenticationService.grpc.GrpcChatServiceImpl;
import com.example.authenticationService.mapper.AgentMapper;
import com.example.authenticationService.model.jooq.schema.enums.Agentlevel;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.AgentEntity;
import com.example.authenticationService.rabbit.RabbitProducer;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.service.AgentService;
import com.example.authenticationService.util.PageRequestUtil;
import dto.EntityPage;
import dto.request.cianuser.agent.AgentRequest;
import dto.request.cianuser.agent.AgentUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.agent.AgentAdminResponse;
import dto.response.cianuser.agent.AgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final CianUserRepository cianUserRepository;

    private final AgentMapper mapper;

    private final RabbitProducer rabbitProducer;

    private final GrpcAdvertisementServiceImpl grpcAdvertisementService;

    private final GrpcChatServiceImpl grpcChatService;


    @Override
    public EntityPage findAll(int page, Map<String, String> columnsAndOrder, List<String> experience, List<String> status) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<AgentEntity> realtyPage = cianUserRepository.findAllAgents(pageRequest, experience, status);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(mapper::fromEntityToResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    public AgentResponse createNewAgent(AgentRequest agentRequest) {
        if (!cianUserRepository.checkExistByLogin(agentRequest.getLogin())) {
            if (!grpcAdvertisementService.checkAgencyExist(agentRequest.getAgencyId())) {
                throw new ThisAgencyNotExist();
            }

            AgentResponse agentResponse = mapper.fromEntityToResponse((AgentEntity) cianUserRepository.save(mapper.fromRequestToEntity(agentRequest), Usertype.AGENT));
            grpcChatService.createNotificationRoom(agentResponse.getId(), agentResponse.getLogin());
            rabbitProducer.sendMessageToVerifyUser(agentResponse.getLogin());
            return agentResponse;
        } else {
            throw new AgentAlreadyExistsException(agentRequest.getLogin());
        }
    }

    @Override
    public CianUserResponse updateAgentById(AgentUpdateRequest updateRequest) {
        rabbitProducer.sendNotification("SERVICE", "Your data were successfully changed!", updateRequest.getId());
        return mapper.fromEntityToResponseUpdate(cianUserRepository.updateById(mapper.fromUpdateRequestToEntity(updateRequest), Usertype.AGENT));
    }

    @Override
    public void deleteById(UUID id) {
        cianUserRepository.deleteById(id);
        grpcChatService.deleteNotificationRoomName(id);
        grpcAdvertisementService.deleteAllRealtyOfUser(id);
    }

    @Override
    public AgentResponse getAgentById(UUID id) {
        return mapper.fromEntityToResponse((AgentEntity) cianUserRepository.findById(id, Usertype.AGENT));
    }

    @Override
    public void hireAgent(UUID agentId, UUID organisationId) {
        rabbitProducer.sendNotification("SERVICE", "Your data were hire by company with id" + organisationId + "!", agentId);
        cianUserRepository.hireAgent(agentId, organisationId);
    }

    @Override
    public void dismissAgent(UUID agentId) {
        rabbitProducer.sendNotification("SERVICE", "Sorry it looks like you were dismiss from your company", agentId);
        cianUserRepository.dismissAgent(agentId);
    }

    @Override
    public void setNewLevel(UUID agentId, Agentlevel agentlevel) {
        rabbitProducer.sendNotification("SERVICE", "You get a new level!", agentId);
        cianUserRepository.setNewLevel(agentId, agentlevel);
    }

    @Override
    public List<AgentAdminResponse> getAgentsOfAgency(UUID agencyId) {
        return cianUserRepository.findAgentsOfAgency(agencyId).stream().map(mapper::fromEntityToAdminResponse).collect(Collectors.toList());
    }

}
