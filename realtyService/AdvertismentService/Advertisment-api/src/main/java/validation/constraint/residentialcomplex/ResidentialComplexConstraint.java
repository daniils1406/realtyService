package validation.constraint.residentialcomplex;

import validation.validator.residentialcomplex.ResidentialComplexUpdateValidator;
import validation.validator.residentialcomplex.ResidentialComplexValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = {ResidentialComplexValidator.class, ResidentialComplexUpdateValidator.class})
public @interface ResidentialComplexConstraint {
    String message() default "{builderConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
