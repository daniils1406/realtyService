package dto.response.agency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgencyAdminResponse {
    private UUID id;
    private Date insertDate;
    private String name;
    private String description;
    private String phoneNumber;
    private String linkForWebsite;
    private String level;
    private String status;
    private List<String> regions;
}
