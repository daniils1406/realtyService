package validation.constraint.newlogin;

import validation.validator.cianUser.CianUserUpdateValidator;
import validation.validator.cianUser.CianUserValidator;
import validation.validator.newlogin.LoginAndTokenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {LoginAndTokenValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface LoginAndTokenConstraint {
    String message() default "{loginAndTokenConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
