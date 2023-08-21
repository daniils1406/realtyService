package validation.validator.newlogin;

import dto.request.LoginRequest;
import dto.request.cianuser.agent.AgentUpdateRequest;
import validation.constraint.agent.AgentConstraint;
import validation.constraint.newlogin.LoginAndTokenConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LoginAndTokenValidator implements ConstraintValidator<LoginAndTokenConstraint, LoginRequest> {

    private static final String MAIL_MISTAKE = "Make sure that the specified mail is correct";

    @Override
    public boolean isValid(LoginRequest value, ConstraintValidatorContext context) {
        if (!Pattern.matches("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$", value.getNewLogin())) {
            buildConstraintViolationWithTemplate(context, MAIL_MISTAKE, "email");
            return false;
        }
        return true;
    }


    private void buildConstraintViolationWithTemplate(ConstraintValidatorContext context, String message, String fieldName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
