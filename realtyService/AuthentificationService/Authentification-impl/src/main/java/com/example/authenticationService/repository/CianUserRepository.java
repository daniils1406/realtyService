package com.example.authenticationService.repository;

import com.example.authenticationService.model.jooq.schema.enums.Agentlevel;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.AgentEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;

import java.util.List;
import java.util.UUID;

import com.example.authenticationService.model.jooq.schema.tables.pojos.OrganisationEntity;
import dto.response.cianuser.agent.AgentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CianUserRepository {
    CianUserEntity save(CianUserEntity entity, Usertype usertype);

    CianUserEntity updateById(CianUserEntity entity,Usertype usertype);

    CianUserEntity findById(UUID id, Usertype usertype);

    void deleteById(UUID id);

    Page<CianUserEntity> findAllCianUsers(Pageable pageable, List<String> role , List<String> status);

    Page<AgentEntity> findAllAgents(Pageable pageable, List<String> experience , List<String> status);

    Page<OrganisationEntity> findAllOrganisations(Pageable pageable , List<String> status);

    void approveById(UUID agencyId);

    CianUserEntity findByLogin(String login);

    boolean checkExistByLogin(String login);

    boolean checkExistById(UUID id);

    void bannedById(UUID id);

    void resetPasswordById(String password,UUID idOfUser);

    void resetLoginById(String login,UUID idOfUser);

    void setRedisId(UUID id, String redisId);

    Usertype findOutUsertypeOfId(UUID id);

    void setRoleOnOwner(UUID id);

    void hireAgent(UUID workerId,UUID organisationId);

    void dismissAgent(UUID workerId);

    void setNewLevel(UUID agentId, Agentlevel agentlevel);

    List<AgentEntity> findAgentsOfAgency(UUID agencyId);
}
