package com.example.authenticationService.repository.impl;

import com.example.authenticationService.exception.EntityNotFoundException;
import com.example.authenticationService.exception.WrongUsertypeEnumException;
import com.example.authenticationService.exception.agent.AgentNotFoundException;
import com.example.authenticationService.exception.cianuser.CianUserNotFoundException;
import com.example.authenticationService.exception.organisation.OrganisationNotFoundException;
import com.example.authenticationService.mapper.jooq.CianUserUnmapper;
import com.example.authenticationService.model.jooq.schema.enums.Agentlevel;
import com.example.authenticationService.model.jooq.schema.enums.Role;
import com.example.authenticationService.model.jooq.schema.enums.Status;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.AgentEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.OrganisationEntity;
import com.example.authenticationService.model.jooq.schema.tables.records.AgentRecord;
import com.example.authenticationService.model.jooq.schema.tables.records.CianUserRecord;
import com.example.authenticationService.model.jooq.schema.tables.records.OrganisationRecord;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.repository.UtilRepository;
import com.example.authenticationService.model.jooq.schema.Tables;
import dto.response.cianuser.agent.AgentResponse;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.example.authenticationService.model.jooq.schema.Tables.AGENT_ENTITY;

@Repository
@RequiredArgsConstructor
public class CianUserRepositoryImpl implements CianUserRepository {

    private final DSLContext dslContext;

    private final CianUserUnmapper cianUserUnmapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public CianUserEntity save(CianUserEntity entity, Usertype usertype) {
        entity.setCreateDate(LocalDate.now());
        entity.setUpdateDate(LocalDate.now());
        entity.setStatus(Status.REGISTERED);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        switch (usertype) {
            case CLIENT -> {
                entity.setRole(Role.CLIENT);
                entity.setUserType(Usertype.CLIENT);
                CianUserRecord record = dslContext.newRecord(Tables.CIAN_USER_ENTITY, entity);
                UUID id = dslContext.insertInto(Tables.CIAN_USER_ENTITY)
                        .set(record)
                        .returning()
                        .fetchOne(Tables.CIAN_USER_ENTITY.ID);
                dslContext.update(Tables.CIAN_USER_ENTITY)
                        .set(Tables.CIAN_USER_ENTITY.LOGO, id + "_LOGO")
                        .execute();
                return findById(id, Usertype.CLIENT);
            }
            case ORGANISATION -> {
                entity.setUserType(Usertype.ORGANISATION);
                CianUserRecord record = dslContext.newRecord(Tables.CIAN_USER_ENTITY, entity);
                entity.setUserType(Usertype.ORGANISATION);
                UUID id = dslContext.insertInto(Tables.CIAN_USER_ENTITY)
                        .set(record)
                        .returning()
                        .fetchOne(Tables.CIAN_USER_ENTITY.ID);
                dslContext.update(Tables.CIAN_USER_ENTITY)
                        .set(Tables.CIAN_USER_ENTITY.LOGO, id + "_LOGO")
                        .execute();
                entity.setId(id);
                OrganisationRecord organisationRecord = dslContext.newRecord(Tables.ORGANISATION_ENTITY, entity);
                dslContext.insertInto(Tables.ORGANISATION_ENTITY)
                        .set(organisationRecord)
                        .execute();
                return findById(id, Usertype.ORGANISATION);

            }
            case AGENT -> {
                entity.setRole(Role.AGENT);
                entity.setUserType(Usertype.AGENT);
                CianUserRecord record = dslContext.newRecord(Tables.CIAN_USER_ENTITY, entity);
                UUID id = dslContext.insertInto(Tables.CIAN_USER_ENTITY)
                        .set(record)
                        .returning()
                        .fetchOne(Tables.CIAN_USER_ENTITY.ID);
                dslContext.update(Tables.CIAN_USER_ENTITY)
                        .set(Tables.CIAN_USER_ENTITY.LOGO, id + "_LOGO")
                        .execute();
                entity.setId(id);
                AgentRecord agentRecord = dslContext.newRecord(AGENT_ENTITY, entity);
                agentRecord.setExperience(Agentlevel.STARTING);
                dslContext.insertInto(AGENT_ENTITY)
                        .set(agentRecord)
                        .execute();
                return findById(id, Usertype.AGENT);
            }

        }
        throw new WrongUsertypeEnumException();
    }

    @Override
    public CianUserEntity updateById(CianUserEntity entity, Usertype usertype) {
        entity.setUpdateDate(LocalDate.now());
        return dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(cianUserUnmapper.unmap(entity))
                .where(Tables.CIAN_USER_ENTITY.ID.eq(entity.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new CianUserNotFoundException(entity.getId()))
                .into(CianUserEntity.class);
    }


    @Override
    public Page<CianUserEntity> findAllCianUsers(Pageable pageable, List<String> role, List<String> status) {
        List<CianUserEntity> users = dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                .where(Tables.CIAN_USER_ENTITY.STATUS.in(status))
                .and(Tables.CIAN_USER_ENTITY.ROLE.in(role))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Tables.CIAN_USER_ENTITY.getClass(), Tables.CIAN_USER_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(CianUserEntity.class);

        Long totalPages = dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                .where(Tables.CIAN_USER_ENTITY.ROLE.in(role))
                .and(Tables.CIAN_USER_ENTITY.STATUS.in(status))
                .stream().count();
        return new PageImpl<>(users, pageable, totalPages);
    }

    @Override
    public Page<AgentEntity> findAllAgents(Pageable pageable, List<String> experience, List<String> status) {
        List<AgentEntity> users = dslContext.select()
                .from(Tables.CIAN_USER_ENTITY)
                .join(AGENT_ENTITY)
                .on(AGENT_ENTITY.ID.eq(Tables.CIAN_USER_ENTITY.ID))
                .where(Tables.CIAN_USER_ENTITY.STATUS.in(status))
                .and(AGENT_ENTITY.EXPERIENCE.in(experience))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Tables.CIAN_USER_ENTITY.getClass(), Tables.CIAN_USER_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(AgentEntity.class);

        Long totalPages = dslContext.select()
                .from(Tables.CIAN_USER_ENTITY)
                .join(AGENT_ENTITY)
                .on(AGENT_ENTITY.ID.eq(Tables.CIAN_USER_ENTITY.ID))
                .where(Tables.CIAN_USER_ENTITY.STATUS.in(status))
                .and(AGENT_ENTITY.EXPERIENCE.in(experience))
                .stream().count();
        return new PageImpl<>(users, pageable, totalPages);
    }

    @Override
    public Page<OrganisationEntity> findAllOrganisations(Pageable pageable, List<String> status) {
        List<OrganisationEntity> users = dslContext.select()
                .from(Tables.CIAN_USER_ENTITY)
                .join(Tables.ORGANISATION_ENTITY)
                .on(Tables.ORGANISATION_ENTITY.ID.eq(Tables.CIAN_USER_ENTITY.ID))
                .where(Tables.CIAN_USER_ENTITY.STATUS.in(status))
                .orderBy(UtilRepository.getSortFields(pageable.getSort(), Tables.CIAN_USER_ENTITY.getClass(), Tables.CIAN_USER_ENTITY))
                .limit(pageable.getOffset(), pageable.getPageSize())
                .fetch()
                .into(OrganisationEntity.class);

        Long totalPages = dslContext.select()
                .from(Tables.CIAN_USER_ENTITY)
                .join(Tables.ORGANISATION_ENTITY)
                .on(Tables.ORGANISATION_ENTITY.ID.eq(Tables.CIAN_USER_ENTITY.ID))
                .where(Tables.CIAN_USER_ENTITY.STATUS.in(status))
                .stream().count();
        return new PageImpl<>(users, pageable, totalPages);
    }


    @Override
    public void approveById(UUID agencyId) {
        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.STATUS, Status.VERIFIED)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(agencyId))
                .execute();
    }

    @Override
    public CianUserEntity findByLogin(String login) {
        return dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                .where(Tables.CIAN_USER_ENTITY.LOGIN.eq(login))
                .fetchOptionalInto(CianUserEntity.class)
                .orElseThrow(() -> new CianUserNotFoundException(login));
    }

    @Override
    public CianUserEntity findById(UUID id, Usertype usertype) {
        switch (usertype) {
            case CLIENT -> {
                return dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                        .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                        .fetchOptionalInto(CianUserEntity.class)
                        .orElseThrow(() -> new CianUserNotFoundException(id));
            }
            case ORGANISATION -> {
                return dslContext.select()
                        .from(Tables.CIAN_USER_ENTITY)
                        .join(Tables.ORGANISATION_ENTITY)
                        .on(Tables.CIAN_USER_ENTITY.ID.eq(Tables.ORGANISATION_ENTITY.ID))
                        .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                        .fetchOptionalInto(OrganisationEntity.class)
                        .orElseThrow(() -> new OrganisationNotFoundException(id));
            }
            case AGENT -> {
                return dslContext.select()
                        .from(Tables.CIAN_USER_ENTITY)
                        .join(AGENT_ENTITY)
                        .on(Tables.CIAN_USER_ENTITY.ID.eq(AGENT_ENTITY.ID))
                        .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                        .fetchOptionalInto(AgentEntity.class)
                        .orElseThrow(() -> new AgentNotFoundException(id));
            }
        }
        throw new WrongUsertypeEnumException();
    }

    @Override
    public boolean checkExistByLogin(String login) {
        return dslContext.fetchExists(dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                .where(Tables.CIAN_USER_ENTITY.LOGIN.eq(login)));
    }

    @Override
    public boolean checkExistById(UUID id) {
        return dslContext.fetchExists(dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(id)));
    }


    @Override
    public void deleteById(UUID id) {
        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.STATUS, Status.DELETE)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public void bannedById(UUID id) {
        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.STATUS, Status.BANNED)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public void resetPasswordById(String password, UUID idOfUser) {

        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.PASSWORD, passwordEncoder.encode(password))
                .where(Tables.CIAN_USER_ENTITY.ID.eq(idOfUser))
                .execute();
    }

    @Override
    public void resetLoginById(String login, UUID idOfUser) {

        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.LOGIN, login)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(idOfUser))
                .execute();
    }

    @Override
    public void setRedisId(UUID id, String redisId) {
        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.REDIS_ID, redisId)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public Usertype findOutUsertypeOfId(UUID id) {
        return dslContext.selectFrom(Tables.CIAN_USER_ENTITY)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                .fetchOptional(Tables.CIAN_USER_ENTITY.USER_TYPE)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    public void setRoleOnOwner(UUID id) {
        dslContext.update(Tables.CIAN_USER_ENTITY)
                .set(Tables.CIAN_USER_ENTITY.ROLE, Role.OWNER)
                .where(Tables.CIAN_USER_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public void hireAgent(UUID workerId, UUID organisationId) {
        dslContext.update(AGENT_ENTITY)
                .set(AGENT_ENTITY.AGENCY_ID, organisationId)
                .where(AGENT_ENTITY.ID.eq(workerId))
                .execute();
    }

    @Override
    public void dismissAgent(UUID workerId) {
        dslContext.update(AGENT_ENTITY)
                .set(AGENT_ENTITY.AGENCY_ID, workerId)
                .where(AGENT_ENTITY.ID.eq(workerId))
                .execute();
    }

    @Override
    public void setNewLevel(UUID agentId, Agentlevel agentlevel) {
        dslContext.update(AGENT_ENTITY)
                .set(AGENT_ENTITY.EXPERIENCE, agentlevel)
                .where(AGENT_ENTITY.ID.eq(agentId))
                .execute();
    }

    @Override
    public List<AgentEntity> findAgentsOfAgency(UUID agencyId) {
        return dslContext.selectFrom(AGENT_ENTITY)
                .where(AGENT_ENTITY.AGENCY_ID.eq(agencyId))
                .fetchInto(AgentEntity.class);
    }
}
