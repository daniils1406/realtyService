package com.example.authenticationService.service;

import com.example.authenticationService.model.jooq.schema.enums.Role;
import com.example.authenticationService.model.jooq.schema.enums.Status;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import dto.EntityPage;
import dto.request.LoginRequest;
import dto.request.PasswordRequest;
import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import dto.response.cianuser.CianUserResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CianUserService {
    EntityPage findAll(int page, Map<String, String> columnsAndOrder, List<String> role, List<String> status);

    CianUserResponse createNewCianUser(CianUserRequest cianUserRequest);

    CianUserResponse updateCianUser(CianUserUpdateRequest cianUserUpdateRequest);

    void deleteById(UUID id);

    CianUserResponse getUserById(UUID id);

    UUID getCompanyOfRepresentative(UUID userId);

    void approveById(String token);

    void bannedById(UUID id);

    void resetPassword(PasswordRequest passwordAndToken);

    void resetLogin(LoginRequest loginRequest);

    void verifySomeUser(UUID id);

    boolean checkExistsUserById(UUID id);

    Usertype findOutUsertypeOfId(UUID id);

    void updateClientToOwner(UUID id);

    Status getUserStatus(UUID id, Usertype usertype);

    Role getUserRole(UUID userId, Usertype usertype);
}
