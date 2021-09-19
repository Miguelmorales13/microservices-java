package micro.mike.security.api.users.validators;

import micro.mike.commons.db.entities.UserEntity;
import micro.mike.security.api.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserUniqueValidator implements ConstraintValidator<UserUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String i, ConstraintValidatorContext constraintValidatorContext) {
        final Optional<UserEntity> wanted = userRepository.findFirstByEmail(i);
        final boolean valid = wanted.isEmpty();
        return valid == true;
    }
}
