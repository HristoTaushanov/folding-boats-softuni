package bg.softuni.boats.model.event;

import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {
    private String username;
    private String email;

    public UserRegisterEvent(Object source, String username, String email) {
        super(source);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
