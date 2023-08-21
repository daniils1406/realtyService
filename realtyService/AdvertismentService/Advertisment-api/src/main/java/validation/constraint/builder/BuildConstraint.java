package validation.constraint.builder;


import validation.validator.builder.BuildContactsValidator;
import validation.validator.builder.BuildUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {BuildContactsValidator.class, BuildUpdateValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface BuildConstraint {
    String message() default "{builderConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
