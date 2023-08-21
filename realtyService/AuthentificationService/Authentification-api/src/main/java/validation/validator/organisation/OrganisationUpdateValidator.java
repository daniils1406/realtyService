package validation.validator.organisation;

import dto.request.cianuser.organisation.OrganisationUpdateRequest;
import validation.constraint.organisation.OrganisationConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class OrganisationUpdateValidator implements ConstraintValidator<OrganisationConstraint, OrganisationUpdateRequest> {

    private static final String CONTACT_MISTAKE = "You must provide a phone number";

    @Override
    public boolean isValid(OrganisationUpdateRequest value, ConstraintValidatorContext context) {
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
