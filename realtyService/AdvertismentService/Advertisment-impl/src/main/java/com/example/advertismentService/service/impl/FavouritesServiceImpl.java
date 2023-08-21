package com.example.advertismentService.service.impl;

import com.example.advertismentService.model.jooq.schema.tables.pojos.FavouritesEntity;
import com.example.advertismentService.service.FavouritesService;
import dto.response.favourites.FavouriteResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.mapper.FavouriteMapper;
import com.example.advertismentService.repository.FavouritesRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepository favouritesRepository;

    private final FavouriteMapper favouriteMapper;

    @Override
    public List<FavouriteResponse> getAllOfUser(UUID userIdFromToken) {
        List<FavouriteResponse> favouriteResponses=new LinkedList<>();
        for(FavouritesEntity favouritesEntity:favouritesRepository.findAll(userIdFromToken)){
            favouriteResponses.add(favouriteMapper.fromEntityToResponse(favouritesEntity));
        }
        return favouriteResponses;
    }

    @Override
    public void deleteFromFavourites(UUID userIdFromToken, UUID realtyId) {
        favouritesRepository.delete(userIdFromToken,realtyId);
    }

    @Override
    public void addFavourites(UUID userIdFromToken, UUID realtyId) {
        favouritesRepository.add(userIdFromToken,realtyId);
    }
}
