package micro.mike.security.api.users.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserUniqueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserUnique {
    String message() default "message default";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
