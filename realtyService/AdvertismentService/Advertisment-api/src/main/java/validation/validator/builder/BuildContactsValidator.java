package validation.validator.builder;

import dto.request.builder.BuilderRequest;
import validation.constraint.builder.BuildConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class BuildContactsValidator implements ConstraintValidator<BuildConstraint, BuilderRequest> {

    private static final String CONTACT_MISTAKE = "The builder must have at least one contact";
    private static final String DATE_MISTAKE = "The developer could not be created on this date";


    @Override
    public boolean isValid(BuilderRequest request, ConstraintValidatorContext context) {
        if (request.getFoundationYear().after(new Date())) {
            buildConstraintViolationWithTemplate(context, DATE_MISTAKE, "Foundation year");
        }
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
