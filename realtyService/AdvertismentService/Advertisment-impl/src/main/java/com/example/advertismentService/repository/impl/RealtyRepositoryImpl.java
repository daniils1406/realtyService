package com.example.advertismentService.repository.impl;

import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.example.advertismentService.model.jooq.schema.enums.Ownertype;
import com.example.advertismentService.model.jooq.schema.enums.Realtystatus;
import com.example.advertismentService.model.jooq.schema.enums.Realtytype;
import com.example.advertismentService.model.jooq.schema.tables.Flat;
import com.example.advertismentService.model.jooq.schema.tables.House;
import com.example.advertismentService.model.jooq.schema.tables.Realty;
import com.example.advertismentService.model.jooq.schema.tables.pojos.FlatEntity;
import com.example.advertismentService.model.jooq.schema.tables.pojos.HouseEntity;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RealtyEntity;
import com.example.advertismentService.model.jooq.schema.tables.records.FlatRecord;
import com.example.advertismentService.model.jooq.schema.tables.records.HouseRecord;
import com.example.advertismentService.model.jooq.schema.tables.records.RealtyRecord;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.exception.EntityNotFoundException;
import com.example.advertismentService.exception.WrongEnumReceived;
import com.example.advertismentService.exception.realty.flat.FlatNotFoundException;
import com.example.advertismentService.exception.realty.house.HouseNotFoundException;
import com.example.advertismentService.mapper.jooq.RealtyJooqUnmapper;
import com.example.advertismentService.repository.RealtyRepository;

import org.jooq.DSLContext;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class RealtyRepositoryImpl implements RealtyRepository {

    private final DSLContext dslContext;

    private final RealtyJooqUnmapper realtyUnmapper;

    @Override
    public Page<RealtyEntity> findAll(Pageable pageable, List<String> realtyStatus, String realtyType,
                                      List<String> advertType, FlatOrHouse flatOrHouse) {
        switch (flatOrHouse) {
            case FLAT -> {
                List<FlatEntity> flatRealty = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.FLAT_TYPE.eq(Realtytype.valueOf(realtyType)))
                        .and(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .fetch()
                        .into(FlatEntity.class);
                Long totalPages = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.FLAT_TYPE.eq(Realtytype.valueOf(realtyType)))
                        .and(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .stream().count();
                return new PageImpl<>(flatRealty.stream().map(entity ->
                        (RealtyEntity) entity).collect(Collectors.toList()), pageable, totalPages);
            }
            case HOUSE -> {
                List<HouseEntity> houseRealty = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(House.HOUSE_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(House.HOUSE_ENTITY.ID))
                        .where(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .fetch()
                        .into(HouseEntity.class);
                Long totalPages = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .stream().count();
                return new PageImpl<>(houseRealty.stream().map(entity ->
                        (RealtyEntity) entity).collect(Collectors.toList()), pageable, totalPages);
            }
        }
        throw new WrongEnumReceived();
    }

    @Override
    public Page<RealtyEntity> findAllForAgent(Pageable pageable, List<String> realtyStatus, String realtyType, List<String> advertType, FlatOrHouse flatOrHouse, UUID agentId) {
        switch (flatOrHouse) {
            case FLAT -> {
                List<FlatEntity> flatRealty = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.FLAT_TYPE.eq(Realtytype.valueOf(realtyType)))
                        .and(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .and(Realty.REALTY_ENTITY.OWNER_TYPE.eq(Ownertype.CLIENT).or(Realty.REALTY_ENTITY.OWNER_ID.eq(agentId)))
                        .fetch()
                        .into(FlatEntity.class);
                Long totalPages = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.FLAT_TYPE.eq(Realtytype.valueOf(realtyType)))
                        .and(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .and(Realty.REALTY_ENTITY.OWNER_TYPE.eq(Ownertype.CLIENT).or(Realty.REALTY_ENTITY.OWNER_ID.eq(agentId)))
                        .stream().count();
                return new PageImpl<>(flatRealty.stream().map(entity ->
                        (RealtyEntity) entity).collect(Collectors.toList()), pageable, totalPages);
            }
            case HOUSE -> {
                List<HouseEntity> houseRealty = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(House.HOUSE_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(House.HOUSE_ENTITY.ID))
                        .where(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .and(Realty.REALTY_ENTITY.OWNER_TYPE.eq(Ownertype.CLIENT).or(Realty.REALTY_ENTITY.OWNER_ID.eq(agentId)))
                        .fetch()
                        .into(HouseEntity.class);
                Long totalPages = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.STATUS.in(realtyStatus))
                        .and(Realty.REALTY_ENTITY.OWNER_TYPE.eq(Ownertype.CLIENT).or(Realty.REALTY_ENTITY.OWNER_ID.eq(agentId)))
                        .stream().count();
                return new PageImpl<>(houseRealty.stream().map(entity ->
                        (RealtyEntity) entity).collect(Collectors.toList()), pageable, totalPages);
            }
        }
        throw new WrongEnumReceived();
    }

    @Override
    public Page<RealtyEntity> findAllOfOwner(Pageable pageable, List<String> realtyStatus, String realtyType, List<String> advertType, FlatOrHouse flatOrHouse, UUID ownerId) {
        switch (flatOrHouse) {
            case FLAT -> {
                List<FlatEntity> flatRealty = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.FLAT_TYPE.eq(Realtytype.valueOf(realtyType)))
                        .and(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                        .fetch()
                        .into(FlatEntity.class);
                Long totalPages = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.FLAT_TYPE.eq(Realtytype.valueOf(realtyType)))
                        .and(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                        .stream().count();
                return new PageImpl<>(flatRealty.stream().map(entity ->
                        (RealtyEntity) entity).collect(Collectors.toList()), pageable, totalPages);
            }
            case HOUSE -> {
                List<HouseEntity> houseRealty = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(House.HOUSE_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(House.HOUSE_ENTITY.ID))
                        .where(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                        .fetch()
                        .into(HouseEntity.class);
                Long totalPages = dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY)
                        .on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Realty.REALTY_ENTITY.ADVERT_TYPE.in(advertType))
                        .and(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                        .stream().count();
                return new PageImpl<>(houseRealty.stream().map(entity ->
                        (RealtyEntity) entity).collect(Collectors.toList()), pageable, totalPages);
            }
        }
        throw new WrongEnumReceived();
    }

    @Override
    public RealtyEntity save(RealtyEntity entity, FlatOrHouse flatOrHouse) {
        entity.setInsertDate(LocalDate.now());
        entity.setUpdateDate(LocalDate.now());
        RealtyRecord realtyRecord = dslContext.newRecord(Realty.REALTY_ENTITY, entity);
        switch (flatOrHouse) {
            case FLAT -> {
                UUID id = dslContext.insertInto(Realty.REALTY_ENTITY)
                        .set(realtyRecord)
                        .returning()
                        .fetchOne(Realty.REALTY_ENTITY.ID);
                entity.setId(id);
                FlatRecord flatRecord = dslContext.newRecord(Flat.FLAT_ENTITY, entity);
                dslContext.insertInto(Flat.FLAT_ENTITY)
                        .set(flatRecord)
                        .execute();
                return findById(id, FlatOrHouse.FLAT);
            }
            case HOUSE -> {
                UUID id = dslContext.insertInto(Realty.REALTY_ENTITY)
                        .set(realtyRecord)
                        .returning()
                        .fetchOne(Realty.REALTY_ENTITY.ID);
                entity.setId(id);
                HouseRecord houseRecord = dslContext.newRecord(House.HOUSE_ENTITY, entity);
                dslContext.insertInto(House.HOUSE_ENTITY)
                        .set(houseRecord)
                        .execute();
                return findById(id, FlatOrHouse.HOUSE);
            }
        }
        throw new WrongEnumReceived();
    }

    @Override
    public RealtyEntity updateById(RealtyEntity entity, FlatOrHouse flatOrHouse) {
        entity.setUpdateDate(LocalDate.now());
        switch (flatOrHouse) {
            case FLAT -> {
                dslContext.update(Realty.REALTY_ENTITY)
                        .set(realtyUnmapper.unmap(entity))
                        .where(Realty.REALTY_ENTITY.ID.eq(entity.getId()))
                        .execute();
                FlatRecord flatRecord = dslContext.newRecord(Flat.FLAT_ENTITY, entity);
                dslContext.update(Flat.FLAT_ENTITY)
                        .set(flatRecord)
                        .where(Flat.FLAT_ENTITY.ID.eq(entity.getId()))
                        .execute();
                return findById(entity.getId(), FlatOrHouse.FLAT);
            }
            case HOUSE -> {
                dslContext.update(Realty.REALTY_ENTITY)
                        .set(realtyUnmapper.unmap(entity))
                        .where(Realty.REALTY_ENTITY.ID.eq(entity.getId()))
                        .execute();
                HouseRecord houseRecord = dslContext.newRecord(House.HOUSE_ENTITY, entity);
                dslContext.update(House.HOUSE_ENTITY)
                        .set(houseRecord)
                        .where(House.HOUSE_ENTITY.ID.eq(entity.getId()))
                        .execute();
                return findById(entity.getId(), FlatOrHouse.HOUSE);
            }
        }
        throw new WrongEnumReceived();
    }

    @Override
    public RealtyEntity findById(UUID id, FlatOrHouse flatOrHouse) {
        switch (flatOrHouse) {
            case FLAT -> {
                return dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(Flat.FLAT_ENTITY).on(Realty.REALTY_ENTITY.ID.eq(Flat.FLAT_ENTITY.ID))
                        .where(Flat.FLAT_ENTITY.ID.eq(id))
                        .fetchOptional()
                        .orElseThrow(() -> new FlatNotFoundException(id))
                        .into(FlatEntity.class);
            }
            case HOUSE -> {
                return dslContext.select()
                        .from(Realty.REALTY_ENTITY)
                        .join(House.HOUSE_ENTITY).on(Realty.REALTY_ENTITY.ID.eq(House.HOUSE_ENTITY.ID))
                        .where(House.HOUSE_ENTITY.ID.eq(id))
                        .fetchOptional()
                        .orElseThrow(() -> new HouseNotFoundException(id))
                        .into(HouseEntity.class);
            }
        }
        throw new WrongEnumReceived();
    }


    @Override
    public void setStatus(UUID flatId, String newStatus) {
        dslContext.update(Realty.REALTY_ENTITY)
                .set(Realty.REALTY_ENTITY.STATUS, Realtystatus.valueOf(newStatus))
                .where(Realty.REALTY_ENTITY.ID.eq(flatId))
                .execute();
    }

    @Override
    public void deleteAllRealtyOfSomeOwner(UUID ownerId) {
        dslContext.update(Realty.REALTY_ENTITY)
                .set(Realty.REALTY_ENTITY.STATUS, Realtystatus.DELETE)
                .where(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                .execute();
    }

    @Override
    public void bannedAllRealtyOfSomeOwner(UUID ownerId) {
        dslContext.update(Realty.REALTY_ENTITY)
                .set(Realty.REALTY_ENTITY.VERIFY, false)
                .where(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                .execute();
    }

    @Override
    public void verifyAllRealtyOfSomeOwner(UUID ownerId) {
        dslContext.update(Realty.REALTY_ENTITY)
                .set(Realty.REALTY_ENTITY.VERIFY, true)
                .where(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
                .execute();
    }

    @Override
    public boolean checkExists(UUID ownerId) {
        return dslContext.fetchExists(
                dslContext.selectFrom(Realty.REALTY_ENTITY)
                        .where(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId))
        );
    }

    @Override
    public void bannedAllRealtyOfResidentialComplex(UUID residentialComplexId) {
        dslContext.update(Realty.REALTY_ENTITY)
                .set(Realty.REALTY_ENTITY.STATUS, Realtystatus.DELETE)
                .where(Realty.REALTY_ENTITY.COMPLEX_ID.eq(residentialComplexId))
                .execute();
    }

    @Override
    public FlatOrHouse findOutTypeOfRealty(UUID realtyId) {
        return dslContext.selectFrom(Realty.REALTY_ENTITY)
                .where(Realty.REALTY_ENTITY.ID.eq(realtyId))
                .fetchOptional(Realty.REALTY_ENTITY.FLAT_OR_HOUSE)
                .orElseThrow(() -> new EntityNotFoundException(realtyId.toString()));
    }

    @Override
    public boolean checkExistsRealtyOfThisOwner(UUID ownerId) {
        return dslContext.fetchExists(dslContext.selectFrom(Realty.REALTY_ENTITY)
                .where(Realty.REALTY_ENTITY.OWNER_ID.eq(ownerId)));
    }

    @Override
    public void transferRealtyToAgent(UUID realtyId, UUID agentId) {
        dslContext.update(Realty.REALTY_ENTITY)
                .set(Realty.REALTY_ENTITY.OWNER_ID, agentId)
                .where(Realty.REALTY_ENTITY.ID.eq(realtyId))
                .execute();
    }
}
