package validation.validator.agency;

import dto.request.agency.AgencyRequest;
import dto.request.agency.AgencyUpdateRequest;
import validation.constraint.agency.AgencyConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AgencyUpdateValidator implements ConstraintValidator<AgencyConstraint, AgencyUpdateRequest> {
    private static final String REGIONS_MISTAKE = "The agency must have at least one region";
    private static final String CONTACT_MISTAKE = "The agency must have at least one contact";

    @Override
    public boolean isValid(AgencyUpdateRequest value, ConstraintValidatorContext context) {
        if (value.getRegions().isEmpty()) {
            buildConstraintViolationWithTemplate(context, REGIONS_MISTAKE, "regions");
            return false;
        }
        if (Pattern.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", value.getPhoneNumber())) {
            return true;
        } else if (Pattern.matches("^https?:\\\\/\\\\/(?:www\\\\.)?[-a-zA-Z0-9@:%._\\\\+~#=]{1,256}\\\\.[a-zA-Z0-9()]{1,6}\\\\b(?:[-a-zA-Z0-9()@:%_\\\\+.~#?&\\\\/=]*)$", value.getLinkForWebsite())) {
            return true;
        } else {
            buildConstraintViolationWithTemplate(context, CONTACT_MISTAKE, "phoneNumber");
            return false;
        }
    }

    private void buildConstraintViolationWithTemplate(ConstraintValidatorContext context, String message, String fieldName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }

}
