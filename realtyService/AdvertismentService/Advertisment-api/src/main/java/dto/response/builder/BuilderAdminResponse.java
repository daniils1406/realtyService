package dto.response.builder;

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
public class BuilderAdminResponse {
    private UUID id;
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
    private String status;
    private Date foundationYear;
    private Date insertDate;
}
