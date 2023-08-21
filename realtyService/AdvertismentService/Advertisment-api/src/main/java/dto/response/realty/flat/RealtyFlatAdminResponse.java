package dto.response.realty.flat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RealtyFlatAdminResponse {
    private UUID id;
    private Date insertDate;
    private Date updateDate;
    private Date releaseDate;
    private UUID complexId;
    private String residentialComplexName;
    private UUID ownerId;
    private String ownerName;
    private String ownerLastName;
    private String ownerLevel;
    private Integer square;
    private String region;
    private String district;
    private String address;
    private String description;
    private Integer cost;
    private String tariffType;
    private int numberOfRooms;
    private int level;
    private int kitchenSquare;
    private int livingSquare;
    private String flatType;
    private String advertType;
    private String status;
}
