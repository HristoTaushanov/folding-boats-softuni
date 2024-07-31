package bg.softuni.boats.service;


import bg.softuni.boats.model.event.UserRegisterEvent;

import java.util.List;

public interface EmailService {

    void sendFeedback(String name, String email, String feedback);

    void sendWelcomeEmail(UserRegisterEvent event);
}
