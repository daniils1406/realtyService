package dto.request.cianuser.organisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.organisation.OrganisationConstraint;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@OrganisationConstraint
public class OrganisationUpdateRequest {
    @NotNull
    private UUID id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String patronymic;
    private String phone;
}
