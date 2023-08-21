package dto.request.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.builder.BuildConstraint;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@BuildConstraint
public class BuilderUpdateRequest {
    private UUID id;
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
}
