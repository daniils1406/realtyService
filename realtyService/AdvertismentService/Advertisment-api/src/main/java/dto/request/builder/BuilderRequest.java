package dto.request.builder;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.builder.BuildConstraint;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@BuildConstraint
public class BuilderRequest {
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
    private Date foundationYear;
}
