package validation.validator.builder;

import dto.request.builder.BuilderUpdateRequest;
import validation.constraint.builder.BuildConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class BuildUpdateValidator implements ConstraintValidator<BuildConstraint, BuilderUpdateRequest> {
    private static final String CONTACT_MISTAKE = "The builder must have at least one contact";

    @Override
    public boolean isValid(BuilderUpdateRequest request, ConstraintValidatorContext context) {
        if (Pattern.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", request.getPhoneNumber())) {
            return true;
        } else if (Pattern.matches("^https?:\\\\/\\\\/(?:www\\\\.)?[-a-zA-Z0-9@:%._\\\\+~#=]{1,256}\\\\.[a-zA-Z0-9()]{1,6}\\\\b(?:[-a-zA-Z0-9()@:%_\\\\+.~#?&\\\\/=]*)$", request.getLinkOnWebsite())) {
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
