package com.example.advertismentService.service;

import dto.response.favourites.FavouriteResponse;

import java.util.List;
import java.util.UUID;

public interface FavouritesService {
    List<FavouriteResponse> getAllOfUser(UUID userIdFromToken);

    void deleteFromFavourites(UUID userIdFromToken, UUID realtyId);

    void addFavourites(UUID userIdFromToken, UUID realtyId);
}
