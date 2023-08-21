package validation.constraint.agent;

import validation.validator.agent.AgentUpdateValidator;
import validation.validator.agent.AgentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AgentValidator.class, AgentUpdateValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface AgentConstraint {
    String message() default "{agentConstraint error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
