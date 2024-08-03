package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.event.UserRegisterEvent;
import bg.softuni.boats.service.exception.MessagingRuntimeException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmailServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendFeedbackSuccess() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendFeedback("John Doe", "john.doe@example.com", "This is a feedback message.");

        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendFeedbackMessagingException() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        doThrow(new MessagingRuntimeException(new MessagingException())).when(javaMailSender).send(any(MimeMessage.class));

        assertThrows(RuntimeException.class, () -> emailService.sendFeedback("John Doe", "john.doe@example.com", "This is a feedback message."));
    }

    @Test
    public void testSendWelcomeEmailSuccess() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        UserRegisterEvent event = new UserRegisterEvent(this, "jane.doe@example.com", "testEmail");

        emailService.sendWelcomeEmail(event);

        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendWelcomeEmailMessagingException() throws MessagingException {
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        UserRegisterEvent event = new UserRegisterEvent(this, "jane.doe@example.com", "testEmail");
        doThrow(new MessagingRuntimeException(new MessagingException())).when(javaMailSender).send(any(MimeMessage.class));

        assertThrows(RuntimeException.class, () -> emailService.sendWelcomeEmail(event));
    }
}
