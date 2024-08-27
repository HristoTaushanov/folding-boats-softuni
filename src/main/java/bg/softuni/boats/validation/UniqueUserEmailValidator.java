package bg.softuni.boats.validation;


import bg.softuni.boats.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    private final UserService userService;

    public UniqueUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return userService
                    .findUserByEmail(value)
                    .isEmpty();
        }

        User principalUser = (User) authentication.getPrincipal();
        Object principal = authentication.getPrincipal();

        String principalUsername = principalUser.getUsername();
        String principalEmail = userService.findUserByUsername(principalUsername).getEmail();

        if (!principal.equals("anonymousUser") && value.equals(principalEmail)) {
            return true;
        }

        if (!principal.equals("anonymousUser") && userService.findUserByEmail(value).isEmpty()) {
            return true;
        } else if (userService.findUserByEmail(value).isPresent()){
            return false;
        }

        return userService
                .findUserByEmail(value)
                .isEmpty();
    }

}
