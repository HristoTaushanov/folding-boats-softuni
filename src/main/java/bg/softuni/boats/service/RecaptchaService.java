package bg.softuni.boats.service;




import bg.softuni.boats.model.dto.RecaptchaResponseDTO;

import java.util.Optional;

public interface RecaptchaService {
    Optional<RecaptchaResponseDTO> verify(String token);
}
