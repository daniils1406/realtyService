package validation.constraint.organisation;

import validation.validator.cianUser.CianUserUpdateValidator;
import validation.validator.cianUser.CianUserValidator;
import validation.validator.organisation.OrganisationUpdateValidator;
import validation.validator.organisation.OrganisationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {OrganisationValidator.class, OrganisationUpdateValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface OrganisationConstraint {
    String message() default "{organisationConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
