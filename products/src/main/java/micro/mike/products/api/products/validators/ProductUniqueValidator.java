package micro.mike.products.api.products.validators;

import micro.mike.commons.db.entities.ProductEntity;
import micro.mike.products.api.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class ProductUniqueValidator implements ConstraintValidator<ProductUnique, String> {

    @Autowired
    private ProductRepository userRepository;

    @Override
    public boolean isValid(String i, ConstraintValidatorContext constraintValidatorContext) {
        final Optional<ProductEntity> wanted = userRepository.findFirstByName(i);
        final boolean valid = wanted.isEmpty();
        return valid == true;
    }
}
