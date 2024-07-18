package bg.softuni.boats.model.dto;

import bg.softuni.boats.validation.FieldMatch;
import bg.softuni.boats.validation.UniqueUserEmail;
import bg.softuni.boats.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public record UserRegistrationDTO(

        @NotBlank(message = "Please enter a username")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @UniqueUsername
        String username,
        @Email(message = "Please enter a valid email address")
        @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
        @UniqueUserEmail
        String email,

        @NotBlank(message = "Please enter strong a password")
        @Size(min = 8, max = 160, message = "Password must be between 8 and 16 characters")
        String password,

        String confirmPassword,

        @NotBlank(message = "Please enter your first name")
        @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
        String firstName,
        @NotBlank(message = "Please enter your last name")
        @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
        String lastName) {


    public static UserRegistrationDTO createEmpty() {
        return new UserRegistrationDTO(
                null, null, null, null, null, null);
    }
}
