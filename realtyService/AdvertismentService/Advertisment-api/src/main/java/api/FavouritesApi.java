package api;

import dto.request.IdRequest;
import dto.response.favourites.FavouriteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RequestMapping("/favourites")
public interface FavouritesApi {

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<FavouriteResponse> getFavouritesOfUser(@RequestBody IdRequest idRequest);

    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    void addToFavourites(HttpServletRequest request, @RequestParam("id") UUID realtyId);

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    void deleteFromFavourites(HttpServletRequest request, @RequestParam("id") UUID realtyId);
}
