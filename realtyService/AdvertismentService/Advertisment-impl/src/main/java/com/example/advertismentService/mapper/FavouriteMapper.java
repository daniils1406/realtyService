package com.example.advertismentService.mapper;

import com.example.advertismentService.model.jooq.schema.tables.pojos.FavouritesEntity;
import dto.response.favourites.FavouriteResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {
    FavouriteResponse fromEntityToResponse(FavouritesEntity favouritesEntity);
}
