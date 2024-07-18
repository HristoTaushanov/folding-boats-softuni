package bg.softuni.boats.model.dto;


import bg.softuni.boats.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserProfileEditDTO {
    private Long id;
    private String username;
    @Email(message = "Please enter a valid email address")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @UniqueUserEmail
    private String email;
    @NotBlank(message = "Please enter your first name")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;
    @NotBlank(message = "Please enter your last name")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    public UserProfileEditDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserProfileEditDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileEditDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileEditDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileEditDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileEditDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
