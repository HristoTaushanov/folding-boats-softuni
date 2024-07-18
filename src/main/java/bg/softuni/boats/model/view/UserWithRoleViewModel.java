package bg.softuni.boats.model.view;

import bg.softuni.boats.model.enums.UserRoleEnum;

import java.util.List;

public class UserWithRoleViewModel {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<UserRoleEnum> role;

    public UserWithRoleViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserWithRoleViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserWithRoleViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserWithRoleViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserWithRoleViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserWithRoleViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRoleEnum> getRole() {
        return role;
    }

    public UserWithRoleViewModel setRole(List<UserRoleEnum> role) {
        this.role = role;
        return this;
    }
}
