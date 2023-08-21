package dto.request.cianuser.agent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.agent.AgentConstraint;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AgentConstraint
public class AgentRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String patronymic;
    private String phone;
    private String login;
    @NotEmpty
    private String password;
    private UUID agencyId;
}
