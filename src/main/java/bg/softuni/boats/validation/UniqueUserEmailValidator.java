package bg.softuni.boats.validation;


import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    private final UserService userService;

    public UniqueUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        UserEntity userByEmail = userService.findUserByEmail(value).orElse(null);
        if (userByEmail!=null && userByEmail.getEmail().equals(value)) {
            return true;
        }

        return userService
                .findUserByEmail(value)
                .isEmpty();
    }
}
