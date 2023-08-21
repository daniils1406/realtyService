package com.example.authenticationService.service.impl;

import com.example.authenticationService.exception.TokenExpiredException;
import com.example.authenticationService.exception.cianuser.CianUserAlreadyExistsException;
import com.example.authenticationService.exception.cianuser.DocumentsOfUserNotVerified;
import com.example.authenticationService.grpc.GrpcAdvertisementServiceImpl;
import com.example.authenticationService.grpc.GrpcChatServiceImpl;
import com.example.authenticationService.grpc.GrpcFileServiceImpl;
import com.example.authenticationService.mapper.CianUserMapper;
import com.example.authenticationService.model.jooq.schema.enums.Role;
import com.example.authenticationService.model.jooq.schema.enums.Status;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.OrganisationEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.VerificationTokenEntity;
import com.example.authenticationService.rabbit.RabbitProducer;
import com.example.authenticationService.redis.RedisUserService;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.repository.VerificationTokenRepository;
import com.example.authenticationService.service.CianUserService;
import com.example.authenticationService.util.PageRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.EntityPage;
import dto.request.LoginRequest;
import dto.request.PasswordRequest;
import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import dto.response.cianuser.CianUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
@RequiredArgsConstructor
public class CianUserServiceImpl implements CianUserService {
    private final CianUserRepository cianUserRepository;

    private final CianUserMapper mapper;

    private final VerificationTokenRepository tokenRepository;

    private final RabbitProducer rabbitProducer;

    private final GrpcFileServiceImpl grpcFileService;

    private final GrpcAdvertisementServiceImpl grpcAdvertisementService;

    private final GrpcChatServiceImpl grpcChatService;

    private final RedisUserService redisUserService;


    private final ObjectMapper objectMapper;



    @Value("${jwt.verification_token_expires_time}")
    private long VERIFICATION_TOKEN_EXPIRES_TIME;


    @Override
    public EntityPage findAll(int page, Map<String, String> columnsAndOrder, List<String> role, List<String> status) {
        PageRequest pageRequest = PageRequestUtil.createPageRequest(page, columnsAndOrder);

        Page<CianUserEntity> realtyPage = cianUserRepository.findAllCianUsers(pageRequest, role, status);
        return EntityPage.builder()
                .totalPages(realtyPage.getTotalPages())
                .data(realtyPage.getContent().stream()
                        .map(mapper::fromEntityToResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    public CianUserResponse createNewCianUser(CianUserRequest cianUserRequest) {
        if (!cianUserRepository.checkExistByLogin(cianUserRequest.getLogin())) {
            CianUserResponse cianUserResponse = mapper.fromEntityToResponse(cianUserRepository.save(mapper.fromRequestToEntity(cianUserRequest), Usertype.CLIENT));
            grpcChatService.createNotificationRoom(cianUserResponse.getId(), cianUserResponse.getLogin());
            rabbitProducer.sendMessageToVerifyUser(cianUserRequest.getLogin());
            return cianUserResponse;
        } else {
            throw new CianUserAlreadyExistsException(cianUserRequest.getLogin());
        }
    }

    @Override
    public CianUserResponse updateCianUser(CianUserUpdateRequest cianUserUpdateRequest) {
        CianUserResponse response = mapper.fromEntityToResponse(cianUserRepository.updateById(mapper.fromUpdateRequestToEntity(cianUserUpdateRequest), Usertype.CLIENT));
        rabbitProducer.sendNotification("SERVICE", "Your data were successfully changed!", cianUserUpdateRequest.getId());
        return response;
    }

    @Override
    public void deleteById(UUID id) {
        cianUserRepository.deleteById(id);
        grpcChatService.deleteNotificationRoomName(id);
        grpcAdvertisementService.deleteAllRealtyOfUser(id);
    }

    @Override
    public CianUserResponse getUserById(UUID id) {
        return mapper.fromEntityToResponse(cianUserRepository.findById(id, Usertype.CLIENT));
    }

    @Override
    public UUID getCompanyOfRepresentative(UUID userId) {
        CianUserEntity cianUserEntity = cianUserRepository.findById(userId, Usertype.ORGANISATION);
        OrganisationEntity organisationEntity = (OrganisationEntity) cianUserEntity;
        return organisationEntity.getOrganisationId();
    }


    @Override
    public void resetPassword(PasswordRequest passwordAndToken) {//grpc метод отправляет уведомление что пароль сменили
        VerificationTokenEntity findedToken = tokenRepository.findById(UUID.fromString(passwordAndToken.getToken()));
        long diff = SECONDS.between(LocalDateTime.now(), findedToken.getInsertDate());
        if (Math.abs(diff) > VERIFICATION_TOKEN_EXPIRES_TIME) {
            tokenRepository.deleteById(UUID.fromString(passwordAndToken.getToken()));
            throw new TokenExpiredException();
        } else {
            cianUserRepository.resetPasswordById(passwordAndToken.getPassword(), findedToken.getUserid());
            tokenRepository.deleteById(UUID.fromString(passwordAndToken.getToken()));
            rabbitProducer.sendNotification("SERVICE", "Your password were successfully changed!", findedToken.getUserid());
        }
    }

    @Override
    public void resetLogin(LoginRequest loginRequest) {
        VerificationTokenEntity findedToken = tokenRepository.findById(UUID.fromString(loginRequest.getToken()));
        long diff = SECONDS.between(LocalDateTime.now(), findedToken.getInsertDate());
        if (Math.abs(diff) > VERIFICATION_TOKEN_EXPIRES_TIME) {
            tokenRepository.deleteById(UUID.fromString(loginRequest.getToken()));
            throw new TokenExpiredException();
        } else {
            if (!cianUserRepository.checkExistByLogin(loginRequest.getNewLogin())) {
                cianUserRepository.resetLoginById(loginRequest.getNewLogin(), findedToken.getUserid());
                rabbitProducer.sendNotification("SERVICE", "Your password were successfully changed!", findedToken.getUserid());
                grpcChatService.changeNotificationRoomName(findedToken.getUserid(), loginRequest.getNewLogin());
            } else {
                throw new CianUserAlreadyExistsException(loginRequest.getNewLogin());
            }
        }
    }

    @Override
    public void approveById(String token) {// уведомляем клиента об успещном подвтерждении аккаунта
        VerificationTokenEntity findedToken = tokenRepository.findById(UUID.fromString(token));
        long diff = SECONDS.between(LocalDateTime.now(), findedToken.getInsertDate());
        if (Math.abs(diff) > VERIFICATION_TOKEN_EXPIRES_TIME) {
            tokenRepository.deleteById(UUID.fromString(token));
            throw new TokenExpiredException();
        } else {
            if (grpcFileService.checkForTheNecessaryFiles(findedToken.getUserid())) {
                rabbitProducer.sendNotification("SERVICE", "Your account were successfully verified!", findedToken.getUserid());
                cianUserRepository.approveById(findedToken.getUserid());
                tokenRepository.deleteById(UUID.fromString(token));
            } else {
                throw new DocumentsOfUserNotVerified(findedToken.getUserid().toString());
            }
        }
    }

    @Override
    public void verifySomeUser(UUID id) {//send a request to documentService to check that all documents of this user is verified
        if (grpcFileService.checkForTheNecessaryFiles(id)) {
            cianUserRepository.approveById(id);
        } else {
            throw new DocumentsOfUserNotVerified(id.toString());
        }
    }

    @Override
    public boolean checkExistsUserById(UUID id) {
        return cianUserRepository.checkExistById(id);
    }

    @Override
    public Usertype findOutUsertypeOfId(UUID id) {
        return cianUserRepository.findOutUsertypeOfId(id);
    }

    @Override
    public void updateClientToOwner(UUID id) {
        cianUserRepository.setRoleOnOwner(id);
    }

    @Override
    public Status getUserStatus(UUID id, Usertype usertype) {
        return cianUserRepository.findById(id, usertype).getStatus();
    }

    @Override
    public Role getUserRole(UUID userId, Usertype usertype) {
        return cianUserRepository.findById(userId, usertype).getRole();
    }


    @Override
    public void bannedById(UUID id) {
        Usertype usertype = cianUserRepository.findOutUsertypeOfId(id);
        CianUserEntity user = cianUserRepository.findById(id, usertype);
        cianUserRepository.bannedById(id);
        redisUserService.addAllTokensOfUserToBlackList(user);
        grpcAdvertisementService.deleteAllRealtyOfUser(id);
    }


}
