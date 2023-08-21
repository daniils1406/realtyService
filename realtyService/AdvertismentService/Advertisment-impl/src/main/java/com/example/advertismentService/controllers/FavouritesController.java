package com.example.advertismentService.controllers;

import api.FavouritesApi;
import dto.request.IdRequest;
import dto.response.favourites.FavouriteResponse;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.security.utils.AuthorizationHeaderUtil;
import com.example.advertismentService.security.utils.JwtUtil;
import com.example.advertismentService.service.FavouritesService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FavouritesController implements FavouritesApi {

    private final FavouritesService favouritesService;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final JwtUtil jwtUtil;

    private UUID getUserIdFromToken(HttpServletRequest request) {
        return UUID.fromString(jwtUtil.parse(authorizationHeaderUtil.getToken(request)).getId());
    }


    @Override
    public List<FavouriteResponse> getFavouritesOfUser(IdRequest idRequest) {
        return favouritesService.getAllOfUser(idRequest.getId());
    }

    @Override
    public void addToFavourites(HttpServletRequest request, UUID realtyId) {
        favouritesService.addFavourites(getUserIdFromToken(request), realtyId);
    }

    @Override
    public void deleteFromFavourites(HttpServletRequest request, UUID realtyId) {
        favouritesService.deleteFromFavourites(getUserIdFromToken(request), realtyId);
    }
}
