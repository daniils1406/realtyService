package validation.validator.agent;

import dto.request.cianuser.agent.AgentUpdateRequest;
import validation.constraint.agent.AgentConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AgentUpdateValidator implements ConstraintValidator<AgentConstraint, AgentUpdateRequest> {

    private static final String CONTACT_MISTAKE = "You must provide a phone number";

    @Override
    public boolean isValid(AgentUpdateRequest value, ConstraintValidatorContext context) {
        if (!Pattern.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", value.getPhone())) {
            buildConstraintViolationWithTemplate(context, CONTACT_MISTAKE, "phoneNumber");
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
