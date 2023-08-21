package dto.response.favourites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FavouriteResponse {
    private UUID clientId;
    private UUID realtyId;
}
