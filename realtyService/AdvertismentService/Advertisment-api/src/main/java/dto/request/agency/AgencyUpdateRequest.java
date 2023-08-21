package dto.request.agency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.agency.AgencyConstraint;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AgencyConstraint
public class AgencyUpdateRequest {
    private UUID id;
    private String name;
    private String description;
    private String phoneNumber;
    private String linkForWebsite;

    List<String> regions;
}
