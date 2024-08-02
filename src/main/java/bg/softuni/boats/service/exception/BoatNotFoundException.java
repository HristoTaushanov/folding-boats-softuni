package bg.softuni.boats.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Boat not found")
public class BoatNotFoundException extends RuntimeException {
    public BoatNotFoundException(String message) {
        super(message);
    }
}
