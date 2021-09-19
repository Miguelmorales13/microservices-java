package micro.mike.products.api.products.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductUniqueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductUnique {
    String message() default "message default";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
