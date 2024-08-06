package bg.softuni.boats.web;

import bg.softuni.boats.model.view.FeedbackViewModel;
import bg.softuni.boats.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(FeedbackController.class)
@AutoConfigureMockMvc
class FeedbackControllerTest {

    @MockBean
    private EmailService emailService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    private MockMvc mockMvc;

    private final FeedbackController feedbackController = new FeedbackController(emailService);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FeedbackController(emailService)).build();
    }

    @Test
    void testFeedbackGet() throws Exception {
        when(model.containsAttribute("feedbackViewModel")).thenReturn(false);

        String viewName = feedbackController.feedback(model);

        assertEquals("feedback", viewName);
        verify(model).addAttribute(eq("feedbackViewModel"), any(FeedbackViewModel.class));
    }

    @Test
    void testFeedbackSubmitWithErrors() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = feedbackController.feedbackSubmit(new FeedbackViewModel(), bindingResult, redirectAttributes);

        assertEquals("redirect:feedback", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("feedbackViewModel"), any(FeedbackViewModel.class));
        verify(redirectAttributes).addFlashAttribute(eq("org.springframework.validation.BindingResult.feedbackViewModel"), eq(bindingResult));
        verify(emailService, never()).sendFeedback(anyString(), anyString(), anyString());
    }


    @Test
    void testContactGet() throws Exception {
        when(model.containsAttribute("feedbackViewModel")).thenReturn(false);

        String viewName = feedbackController.contact(model);

        assertEquals("contact", viewName);
        verify(model).addAttribute(eq("feedbackViewModel"), any(FeedbackViewModel.class));
    }

    @Test
    void testContactSubmitWithErrors() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = feedbackController.contact(new FeedbackViewModel(), bindingResult, redirectAttributes);

        assertEquals("redirect:contact", viewName);
        verify(redirectAttributes).addFlashAttribute(eq("feedbackViewModel"), any(FeedbackViewModel.class));
        verify(redirectAttributes).addFlashAttribute(eq("org.springframework.validation.BindingResult.feedbackViewModel"), eq(bindingResult));
        verify(emailService, never()).sendFeedback(anyString(), anyString(), anyString());
    }


    @Test
    void testFeedbackPostRequestWithErrors() throws Exception {
        mockMvc.perform(post("/feedback")
                        .param("name", "")
                        .param("email", "")
                        .param("feedback", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:feedback"));
    }

    @Test
    void testFeedbackPostRequestWithoutErrors() throws Exception {
        mockMvc.perform(post("/feedback")
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com")
                        .param("feedback", "Great service!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));
    }


    @Test
    void testContactPostRequestWithErrors() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "")
                        .param("email", "")
                        .param("feedback", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:contact"));
    }

    @Test
    void testContactPostRequestWithoutErrors() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "Jane Doe")
                        .param("email", "jane.doe@example.com")
                        .param("feedback", "Excellent support!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}
