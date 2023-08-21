package validation.validator.residentialcomplex;

import dto.request.residentialcomplex.ResidentialComplexRequest;
import validation.constraint.residentialcomplex.ResidentialComplexConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ResidentialComplexValidator implements ConstraintValidator<ResidentialComplexConstraint, ResidentialComplexRequest> {

    private final String INCORRECT_NUMBER_OF_READY_BUILDINGS =
            "The number of finished objects %s cannot be more than the planned number %s";

    private static final String CONTACT_MISTAKE = "Residential complex must have at least one contact";


    @Override
    public boolean isValid(ResidentialComplexRequest value, ConstraintValidatorContext context) {
        if (value.getNumberOfBuildings() < value.getNumberOfReadyBuildings() || value.getNumberOfBuildings() <= 0) {
            buildConstraintViolationWithTemplate(context, String.format(INCORRECT_NUMBER_OF_READY_BUILDINGS,
                    value.getNumberOfReadyBuildings(), value.getNumberOfBuildings()), "numberOfReadyBuildings");
            return false;
        }
        if (Pattern.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", value.getPhoneNumber())) {
            return true;
        } else if (Pattern.matches("^https?:\\\\/\\\\/(?:www\\\\.)?[-a-zA-Z0-9@:%._\\\\+~#=]{1,256}\\\\.[a-zA-Z0-9()]{1,6}\\\\b(?:[-a-zA-Z0-9()@:%_\\\\+.~#?&\\\\/=]*)$", value.getLinkOnWebsite())) {
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
