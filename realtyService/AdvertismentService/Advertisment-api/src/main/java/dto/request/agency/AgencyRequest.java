package dto.request.agency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.agency.AgencyConstraint;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AgencyConstraint
public class AgencyRequest {
    private String name;
    private String description;
    private String phoneNumber;
    private String linkForWebsite;

    List<String> regions;
}
