package com.example.advertismentService.service.impl;

import authentication.CianUserResponse;
import authentication.Role;
import com.example.advertismentService.grpc.GrpcAuthenticationServiceImpl;
import com.example.advertismentService.grpc.GrpcFileServiceImpl;
import com.example.advertismentService.mapper.RealtyFlatMapper;
import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.example.advertismentService.model.jooq.schema.enums.Realtystatus;
import com.example.advertismentService.model.jooq.schema.tables.pojos.FlatEntity;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RealtyEntity;
import com.example.advertismentService.rabbit.RabbitProducer;
import com.example.advertismentService.service.RealtyFlatService;
import dto.EntityPage;
import dto.request.realty.flat.RealtyFlatRequest;
import dto.request.realty.flat.RealtyFlatUpdateRequest;
import dto.response.realty.flat.RealtyFlatAdminResponse;
import dto.response.realty.flat.RealtyFlatResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.owner.OwnerNotConfirmedException;
import com.example.advertismentService.exception.residentialcomplex.ResidentialComplexNotFoundException;
import com.example.advertismentService.repository.RealtyRepository;
import com.example.advertismentService.repository.ResidentialComplexRepository;
import com.example.advertismentService.util.PageRequestUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RealtyFlatServiceImpl implements RealtyFlatService {

    private final RealtyRepository realtyRepository;

    private final ResidentialComplexRepository residentialComplexRepository;

    private final RealtyFlatMapper mapper;

    private final GrpcFileServiceImpl grpcFileService;

    private final GrpcAuthenticationServiceImpl grpcAuthenticationService;

    private final RabbitProducer rabbitProducer;

    @Override
    public EntityPage findAll(int page, List<String> realtyStatus, String realtyType, List<String> advertType,
                              Map<String, String> columnsAndOrder) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<RealtyEntity> realtyPage = realtyRepository.findAll(pageRequest, realtyStatus, realtyType, advertType,
                FlatOrHouse.FLAT);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(entity -> {
                            RealtyFlatResponse response = mapper.fromEntityToResponse((FlatEntity) entity);
                            response.setResidentialComplexName(
                                    residentialComplexRepository.findById(entity.getComplexId()).getName());
                            return response;
                        }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public EntityPage findAllForAgent(int page, List<String> realtyStatus, String realtyType, List<String> advertType, Map<String, String> columnsAndOrder, UUID agentId) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<RealtyEntity> realtyPage = realtyRepository.findAllForAgent(pageRequest, realtyStatus, realtyType, advertType,
                FlatOrHouse.FLAT,agentId);

        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(entity -> {
                            RealtyFlatResponse response = mapper.fromEntityToResponse((FlatEntity) entity);
                            response.setResidentialComplexName(
                                    residentialComplexRepository.findById(entity.getComplexId()).getName());
                            return response;
                        }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public EntityPage findAllOfSomeOwner(int page, List<String> realtyStatus, String realtyType, List<String> advertType, Map<String, String> columnsAndOrder, UUID ownerId) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<RealtyEntity> realtyPage = realtyRepository.findAllOfOwner(pageRequest, realtyStatus, realtyType, advertType,
                FlatOrHouse.FLAT, ownerId);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(entity -> {
                            RealtyFlatAdminResponse response = mapper.fromEntityToAdminResponse((FlatEntity) entity);
                            response.setResidentialComplexName(
                                    residentialComplexRepository.findById(entity.getComplexId()).getName());
                            return response;
                        }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public RealtyFlatResponse createFlat(RealtyFlatRequest flat) {//GRPC метод проверяет что owner подтвержденный
        if (grpcFileService.checkForTheNecessaryFiles(flat.getOwnerId())) {
            CianUserResponse cianUserResponse=grpcAuthenticationService.findUserById(flat.getOwnerId());
            if (!realtyRepository.checkExistsRealtyOfThisOwner(flat.getOwnerId()) && cianUserResponse.getRole().equals(Role.CLIENT)) {
                grpcAuthenticationService.changeClientToOwner(flat.getOwnerId());
                grpcFileService.setNewEntityTypeInDocumentService(flat.getOwnerId(),"OWNER");
            }
            if (residentialComplexRepository.existById(flat.getComplexId())) {
                if(cianUserResponse.getRole().equals(Role.AGENCY) ||cianUserResponse.getRole().equals(Role.BUILDER)){
                    flat.setVerify(true);
                }
                return mapper.fromEntityToResponse((FlatEntity) realtyRepository.save(mapper.fromRequestToEntity(flat),
                        FlatOrHouse.FLAT));
            }else{
                throw new ResidentialComplexNotFoundException(flat.getComplexId());
            }
        } else {
            throw new OwnerNotConfirmedException(flat.getOwnerId().toString());
        }
    }

    @Override
    public RealtyFlatResponse updateFlat(RealtyFlatUpdateRequest newFlat) {
        RealtyFlatResponse response = mapper.fromEntityToResponse((FlatEntity) realtyRepository.updateById(
                mapper.fromUpdateRequestToEntity(newFlat), FlatOrHouse.FLAT));
        rabbitProducer.sendNotification("SERVICE", "Your realty with address " + newFlat.getAddress() + " were successfully changed!", newFlat.getOwnerId());
        return response;
    }

    @Override
    public RealtyFlatResponse findById(UUID flatId) {
        RealtyFlatResponse response = mapper.fromEntityToResponse((FlatEntity) realtyRepository.findById(flatId,
                FlatOrHouse.FLAT));
        response.setResidentialComplexName(residentialComplexRepository.findById(response.getComplexId()).getName());
        return response;
    }

    @Override
    public void setStatusFlat(UUID flatId, String newStatus) {
        RealtyFlatResponse response = findById(flatId);
        rabbitProducer.sendNotification("SERVICE", "Your realty with address " + response.getAddress() + " were switch status on " + newStatus + "!", response.getOwnerId());
        realtyRepository.setStatus(flatId, newStatus);
    }

    @Override
    public void interestedInRealty(UUID userId, UUID realtyId) {
        UUID ownerId = findById(realtyId).getOwnerId();
        rabbitProducer.sendNotification("SERVICE", "User with id " + userId + " interested in realty " + realtyId + "", ownerId);
    }

    @Override
    public void transferRealtyToAgent(UUID realtyId, UUID agentId) {
        realtyRepository.transferRealtyToAgent(realtyId,agentId);
        realtyRepository.setStatus(realtyId, Realtystatus.OPEN.toString());
    }

}