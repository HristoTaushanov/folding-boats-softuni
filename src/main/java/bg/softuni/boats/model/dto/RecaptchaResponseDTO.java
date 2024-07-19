package bg.softuni.boats.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecaptchaResponseDTO {
    private boolean success;
    private List<String> errorCodes;

    public RecaptchaResponseDTO() {
    }

    public boolean isSuccess() {
        return success;
    }

    public RecaptchaResponseDTO setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public RecaptchaResponseDTO setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
        return this;
    }
}
