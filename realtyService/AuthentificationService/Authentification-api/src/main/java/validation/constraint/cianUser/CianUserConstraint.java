package validation.constraint.cianUser;

import validation.validator.cianUser.CianUserUpdateValidator;
import validation.validator.cianUser.CianUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CianUserValidator.class, CianUserUpdateValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface CianUserConstraint {
    String message() default "{cianUserConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
