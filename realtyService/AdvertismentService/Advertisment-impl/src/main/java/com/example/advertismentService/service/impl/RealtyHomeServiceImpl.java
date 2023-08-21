package com.example.advertismentService.service.impl;

import authentication.CianUserResponse;
import authentication.Role;
import com.example.advertismentService.grpc.GrpcAuthenticationServiceImpl;
import com.example.advertismentService.grpc.GrpcChatServiceImpl;
import com.example.advertismentService.grpc.GrpcFileServiceImpl;
import com.example.advertismentService.mapper.RealtyHomeMapper;
import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.example.advertismentService.model.jooq.schema.enums.Realtystatus;
import com.example.advertismentService.model.jooq.schema.tables.pojos.HouseEntity;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RealtyEntity;
import com.example.advertismentService.rabbit.RabbitProducer;
import dto.EntityPage;
import dto.request.realty.home.RealtyHomeRequest;
import dto.request.realty.home.RealtyHomeUpdateRequest;
import dto.response.realty.home.RealtyHomeResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.owner.OwnerNotConfirmedException;
import com.example.advertismentService.repository.RealtyRepository;
import com.example.advertismentService.service.RealtyHomeService;
import com.example.advertismentService.util.PageRequestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealtyHomeServiceImpl implements RealtyHomeService {

    private final RealtyRepository realtyRepository;

    private final RealtyHomeMapper mapper;

    private final GrpcFileServiceImpl grpcFileService;

    private final GrpcChatServiceImpl grpcChatService;

    private final GrpcAuthenticationServiceImpl grpcAuthenticationService;

    private final RabbitProducer rabbitProducer;


    @Override
    public EntityPage findAll(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                              Map<String, String> columnsAndOrder) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<RealtyEntity> realtyPage = realtyRepository.findAll(pageRequest, realtyStatus, realtyType, advertType,
                FlatOrHouse.HOUSE);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(entity -> mapper.fromEntityToResponse((HouseEntity) entity)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public EntityPage findAllForAgent(int page, List<String> realtyStatus, String realtyType, List<String> advertType, Map<String, String> columnsAndOrder, UUID agentId) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<RealtyEntity> realtyPage = realtyRepository.findAllForAgent(pageRequest, realtyStatus, realtyType, advertType,
                FlatOrHouse.HOUSE, agentId);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(entity -> mapper.fromEntityToResponse((HouseEntity) entity)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public EntityPage findAllOfSomeOwner(int page, List<String> realtyStatus, String realtyType, List<String> advertType, Map<String, String> columnsAndOrder, UUID ownerId) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<RealtyEntity> realtyPage = realtyRepository.findAllOfOwner(pageRequest, realtyStatus, realtyType, advertType,
                FlatOrHouse.HOUSE, ownerId);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(entity -> mapper.fromEntityToAdminResponse((HouseEntity) entity)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public RealtyHomeResponse createHome(RealtyHomeRequest home) {
        if (grpcFileService.checkForTheNecessaryFiles(home.getOwnerId())) {
            CianUserResponse cianUserResponse = grpcAuthenticationService.findUserById(home.getOwnerId());
            if (!realtyRepository.checkExistsRealtyOfThisOwner(home.getOwnerId()) && cianUserResponse.getRole().equals(Role.CLIENT)) {
                grpcAuthenticationService.changeClientToOwner(home.getOwnerId());
                grpcFileService.setNewEntityTypeInDocumentService(home.getOwnerId(), "OWNER");
                throw new OwnerNotConfirmedException();
            }
            if (cianUserResponse.getRole().equals(Role.AGENCY) || cianUserResponse.getRole().equals(Role.BUILDER)) {
                home.setVerify(true);
            }
            return mapper.fromEntityToResponse((HouseEntity) realtyRepository.save(mapper.fromRequestToEntity(home),
                    FlatOrHouse.HOUSE));
        } else {
            throw new OwnerNotConfirmedException(home.getOwnerId().toString());
        }
    }

    @Override
    public RealtyHomeResponse updateHome(RealtyHomeUpdateRequest newHome) {
        RealtyHomeResponse response = mapper.fromEntityToResponse((HouseEntity) realtyRepository.updateById(
                mapper.fromUpdateRequestToEntity(newHome), FlatOrHouse.HOUSE));
        rabbitProducer.sendNotification("SERVICE", "Your realty with address " + newHome.getAddress() + " were successfully changed!", newHome.getOwnerId());
        return response;
    }

    @Override
    public RealtyHomeResponse findById(UUID homeId) {
        return mapper.fromEntityToResponse((HouseEntity) realtyRepository.findById(homeId, FlatOrHouse.HOUSE));
    }

    @Override
    public void setStatusHome(UUID homeId, String newStatus) {
        RealtyHomeResponse response = findById(homeId);
        rabbitProducer.sendNotification("SERVICE", "Your realty with address " + response.getAddress() + " were switch status on " + newStatus + "!", response.getOwnerId());
        realtyRepository.setStatus(homeId, newStatus);
    }

    @Override
    public void interestedInRealty(UUID userId, UUID realtyId) {
        UUID ownerId = findById(realtyId).getOwnerId();
        rabbitProducer.sendNotification("SERVICE", "User with id " + userId + " interested in realty " + realtyId + "", ownerId);
    }

    @Override
    public void transferRealtyToAgent(UUID realtyId, UUID agentId) {
        realtyRepository.transferRealtyToAgent(realtyId, agentId);
        realtyRepository.setStatus(realtyId, Realtystatus.OPEN.toString());
    }
}
