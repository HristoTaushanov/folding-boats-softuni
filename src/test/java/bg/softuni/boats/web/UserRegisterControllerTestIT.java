package bg.softuni.boats.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationGET() throws Exception {
        mockMvc.perform(
                        get("/users/register")
                )
                .andExpect(status().isOk());
    }

    @Test
    void testRegistrationShouldWork() throws Exception {
        mockMvc.perform(
                        post("/user/register")
                                .param("username", "Ivan")
                                .param("email", "ivan@hotmail.com")
                                .param("password", "ivan123")
                                .param("confirmPassword", "ivan123")
                                .param("firstName", "Ivan")
                                .param("lastName", "Ivanov")
                                .param("companyName", "Eiffage")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}
