package dto.request.realty.flat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RealtyFlatUpdateRequest {
    @NotNull
    private UUID id;
    @NotNull
    private Date releaseDate;
    @NotNull
    private UUID complexId;
    @NotNull
    private UUID ownerId;
    @NotNull
    private Integer square;
    @NotNull
    private String region;
    @NotNull
    private String district;
    @NotNull
    private String address;
    private String description;
    @NotNull
    private String flatType;
    @NotNull
    private String advertType;
    @NotNull
    private Integer cost;
    @NotNull
    private String tariffType;
    @NotNull
    private String flatOrHouse;
    @NotNull
    private int numberOfRooms;
    private int level;
    @NotNull
    private int kitchenSquare;
    @NotNull
    private int livingSquare;
    @NotNull
    private String status;
}
