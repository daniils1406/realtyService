package com.example.advertismentService.repository;

import com.example.advertismentService.model.jooq.schema.tables.pojos.FavouritesEntity;

import java.util.List;
import java.util.UUID;

public interface FavouritesRepository {
    List<FavouritesEntity> findAll(UUID userIdFromToken);

    void delete(UUID userIdFromToken, UUID realtyId);

    void add(UUID userIdFromToken, UUID realtyId);
}
