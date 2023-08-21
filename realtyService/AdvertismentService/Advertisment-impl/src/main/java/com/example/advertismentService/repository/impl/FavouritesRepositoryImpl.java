package com.example.advertismentService.repository.impl;

import com.example.advertismentService.model.jooq.schema.Tables;
import com.example.advertismentService.model.jooq.schema.tables.pojos.FavouritesEntity;
import com.example.advertismentService.repository.FavouritesRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FavouritesRepositoryImpl implements FavouritesRepository {

    private final DSLContext dslContext;

    @Override
    public List<FavouritesEntity> findAll(UUID userIdFromToken) {
        return dslContext.selectFrom(Tables.FAVOURITES_ENTITY)
                .where(Tables.FAVOURITES_ENTITY.CLIENT_ID.eq(userIdFromToken))
                .fetchInto(FavouritesEntity.class);
    }

    @Override
    public void delete(UUID userIdFromToken, UUID realtyId) {
        dslContext.deleteFrom(Tables.FAVOURITES_ENTITY)
                .where(Tables.FAVOURITES_ENTITY.CLIENT_ID.eq(userIdFromToken))
                .and(Tables.FAVOURITES_ENTITY.REALTY_ID.eq(realtyId))
                .execute();
    }

    @Override
    public void add(UUID userIdFromToken, UUID realtyId) {
        dslContext.insertInto(Tables.FAVOURITES_ENTITY)
                .set(Tables.FAVOURITES_ENTITY.CLIENT_ID, userIdFromToken)
                .set(Tables.FAVOURITES_ENTITY.REALTY_ID, realtyId)
                .execute();
    }
}
