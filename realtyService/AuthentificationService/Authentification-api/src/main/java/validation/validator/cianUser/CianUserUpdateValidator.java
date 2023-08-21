package validation.validator.cianUser;

import dto.request.cianuser.CianUserRequest;
import dto.request.cianuser.CianUserUpdateRequest;
import validation.constraint.cianUser.CianUserConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CianUserUpdateValidator implements ConstraintValidator<CianUserConstraint, CianUserUpdateRequest> {

    private static final String CONTACT_MISTAKE = "You must provide a phone number";

    @Override
    public boolean isValid(CianUserUpdateRequest value, ConstraintValidatorContext context) {
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
