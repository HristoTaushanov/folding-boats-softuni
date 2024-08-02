package bg.softuni.boats.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class OpinionForm {

    @NotEmpty(message = "Opinion cannot be empty")
    private String message;



    public OpinionForm() {
    }


    public OpinionForm(String message) {
        this.message = message;
    }

    public @NotEmpty(message = "Opinion cannot be empty") String getMessage() {
        return message;
    }

    public OpinionForm setMessage(@NotEmpty(message = "Opinion cannot be empty") String message) {
        this.message = message;
        return this;
    }
}
