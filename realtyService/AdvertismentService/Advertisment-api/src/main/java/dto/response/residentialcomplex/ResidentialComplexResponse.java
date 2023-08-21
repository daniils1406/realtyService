package dto.response.residentialcomplex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResidentialComplexResponse {
    private UUID id;
    private String region;
    private String city;
    private String district;
    private Integer numberOfBuildings;
    private Integer numberOfReadyBuildings;
    private UUID builderId;
    private String builderName;
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
    private Date deliveryYear;
}
