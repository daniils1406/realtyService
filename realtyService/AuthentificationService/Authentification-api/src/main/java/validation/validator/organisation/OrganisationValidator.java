package validation.validator.organisation;

import dto.request.cianuser.CianUserUpdateRequest;
import dto.request.cianuser.organisation.OrganisationRequest;
import validation.constraint.cianUser.CianUserConstraint;
import validation.constraint.organisation.OrganisationConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class OrganisationValidator implements ConstraintValidator<OrganisationConstraint, OrganisationRequest> {

    private static final String CONTACT_MISTAKE = "You must provide a phone number";

    private static final String MAIL_MISTAKE = "Make sure that the specified mail is correct";


    @Override
    public boolean isValid(OrganisationRequest value, ConstraintValidatorContext context) {
        if (!Pattern.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", value.getPhone())) {
            buildConstraintViolationWithTemplate(context, CONTACT_MISTAKE, "phoneNumber");
            return false;
        } else if (!Pattern.matches("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$", value.getLogin())) {
            buildConstraintViolationWithTemplate(context, MAIL_MISTAKE, "email");
            return false;
        } else {
            return true;
        }
    }


    private void buildConstraintViolationWithTemplate(ConstraintValidatorContext context, String message, String fieldName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
