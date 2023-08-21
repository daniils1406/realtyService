package dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.newlogin.LoginAndTokenConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@LoginAndTokenConstraint
public class LoginRequest {
    private String newLogin;
    private String token;
}
