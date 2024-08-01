package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.event.UserRegisterEvent;
import bg.softuni.boats.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendFeedback(String name, String email, String feedback) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo("feedback@somefen.com");
            mimeMessageHelper.setFrom(email);
            mimeMessageHelper.setSubject("New feedback from " + name);
            mimeMessageHelper.setText(feedback);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void sendWelcomeEmail(UserRegisterEvent event) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(event.getEmail());
            mimeMessageHelper.setFrom("visioplan@somefen.com");
            mimeMessageHelper.setSubject("Welcome to SomeFen!");
            mimeMessageHelper.setText("Welcome to SomeFen!", true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


