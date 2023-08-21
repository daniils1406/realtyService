package validation.constraint.deal;

import validation.validator.deal.DealConstraintValidator;
import validation.validator.deal.DealUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {DealConstraintValidator.class, DealUpdateValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface DealConstraint {
    String message() default "{dealConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
