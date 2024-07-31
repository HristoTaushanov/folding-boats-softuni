package bg.softuni.boats.model.view;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackViewModel {
    @Size(min = 3, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Please provide an email address.")
    @Email(message = "Please provide a valid email.")
    private String email;

    @NotNull
    @Size(min = 10, max = 1000, message = "Feedback must be between 10 and 1000 characters")
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
