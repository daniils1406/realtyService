package dto.response.cianuser.agent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String login;
    private UUID agencyId;
}
