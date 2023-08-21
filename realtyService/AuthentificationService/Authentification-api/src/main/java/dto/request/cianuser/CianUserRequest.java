package dto.request.cianuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.cianUser.CianUserConstraint;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CianUserConstraint
public class CianUserRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String patronymic;
    private String phone;
    private String login;
    private String password;
}
