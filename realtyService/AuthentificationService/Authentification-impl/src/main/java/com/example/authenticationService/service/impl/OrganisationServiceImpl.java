package com.example.authenticationService.service.impl;

import com.example.authenticationService.exception.organisation.OrganisationAlreadyExistsException;
import com.example.authenticationService.exception.organisation.ThisOrganisationNotExist;
import com.example.authenticationService.grpc.GrpcAdvertisementServiceImpl;
import com.example.authenticationService.grpc.GrpcChatServiceImpl;
import com.example.authenticationService.mapper.OrganisationMapper;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.OrganisationEntity;
import com.example.authenticationService.rabbit.RabbitProducer;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.service.OrganisationService;
import com.example.authenticationService.util.PageRequestUtil;
import dto.EntityPage;
import dto.request.cianuser.organisation.OrganisationRequest;
import dto.request.cianuser.organisation.OrganisationUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import dto.response.cianuser.organisation.OrganisationResponse;
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
public class OrganisationServiceImpl implements OrganisationService {

    private final CianUserRepository cianUserRepository;

    private final OrganisationMapper mapper;

    private final RabbitProducer rabbitProducer;

    private final GrpcAdvertisementServiceImpl grpcAdvertisementService;

    private final GrpcChatServiceImpl grpcChatService;


    @Override
    public EntityPage findAll(int page, Map<String, String> columnsAndOrder, List<String> status) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<OrganisationEntity> realtyPage = cianUserRepository.findAllOrganisations(pageRequest, status);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(mapper::fromEntityToResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    public OrganisationResponse createNewOrganisation(OrganisationRequest organisationRequest) {
        if (!cianUserRepository.checkExistByLogin(organisationRequest.getLogin())) {
            if (organisationRequest.getRole().equals("AGENCY")) {
                if (!grpcAdvertisementService.checkAgencyExist(organisationRequest.getOrganisationId())) {
                    throw new ThisOrganisationNotExist();
                }
            } else {
                if (!grpcAdvertisementService.checkBuilderExist(organisationRequest.getOrganisationId())) {
                    throw new ThisOrganisationNotExist();
                }
            }

            OrganisationResponse organisationResponse = mapper.fromEntityToResponse((OrganisationEntity) cianUserRepository.save(mapper.fromRequestToEntity(organisationRequest), Usertype.ORGANISATION));
            rabbitProducer.sendMessageToVerifyUser(organisationResponse.getLogin());
            return organisationResponse;
        } else {
            throw new OrganisationAlreadyExistsException(organisationRequest.getLogin());
        }

    }

    @Override
    public CianUserResponse updateOrganisation(OrganisationUpdateRequest organisationUpdateRequest) {
        rabbitProducer.sendNotification("SERVICE", "Your data were successfully changed!", organisationUpdateRequest.getId());
        return mapper.fromEntityToResponseUpdate(cianUserRepository.updateById(mapper.fromUpdateRequestToEntity(organisationUpdateRequest), Usertype.ORGANISATION));
    }

    @Override
    public void deleteOrganisationById(UUID id) {
        cianUserRepository.deleteById(id);
        grpcChatService.deleteNotificationRoomName(id);
        grpcAdvertisementService.deleteAllRealtyOfUser(id);
    }

    @Override
    public OrganisationResponse getOrganisationById(UUID id) {
        return mapper.fromEntityToResponse((OrganisationEntity) cianUserRepository.findById(id, Usertype.ORGANISATION));
    }

}
