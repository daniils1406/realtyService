package dto.request.residentialcomplex;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.residentialcomplex.ResidentialComplexConstraint;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ResidentialComplexConstraint
public class ResidentialComplexRequest {
    private String region;
    private String city;
    private String district;
    private Integer numberOfBuildings;
    private Integer numberOfReadyBuildings;
    private UUID builderId;
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
    private Date deliveryYear;
}
