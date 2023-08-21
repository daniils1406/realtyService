package dto.request.realty.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RealtyHomeRequest {
    @NotNull
    private Date releaseDate;
    private UUID complexId;
    @NotNull
    private UUID ownerId;
    @NotNull
    private Integer square;
    @NotNull
    private String region;
    private String district;
    @NotNull
    private String address;
    private String description;
    @NotNull
    private String status;
    @NotNull
    private String realtyType;
    @NotNull
    private String advertType;
    @NotNull
    private Integer cost;
    @NotNull
    private String tariffType;
    @NotNull
    private String flatOrHouse;
    @NotNull
    private int areaSquare;
    @NotNull
    private String material;
    @NotNull
    private int levels;
    @NotNull
    private String ownerType;
    private boolean verify=false;
}
