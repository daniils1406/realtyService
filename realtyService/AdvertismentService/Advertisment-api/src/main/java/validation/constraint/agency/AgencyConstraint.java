package validation.constraint.agency;

import validation.validator.agency.AgencyUpdateValidator;
import validation.validator.agency.AgencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AgencyValidator.class, AgencyUpdateValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface AgencyConstraint {
    String message() default "{agencyConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
