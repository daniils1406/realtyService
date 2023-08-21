package dto.response.builder;

import dto.response.residentialcomplex.ResidentialComplexResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuilderAdminWithComplexesResponse {
    private UUID id;
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
    private String status;
    private Date foundationYear;
    private Date insertDate;

    private List<ResidentialComplexResponse> residentialComplexesOfBuilder;
}
