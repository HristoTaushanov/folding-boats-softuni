package bg.softuni.boats.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testLoginGET() throws Exception {
        mockMvc.perform(
                        get("/users/login")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("user-login"));
    }

    @Test
    void testLoginWrongInput() throws Exception {
        mockMvc.perform(
                        post("/users/login-error")
                                .param("username", "wrong")
                                .param("password", "wrong")
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("user-login"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(
                        post("/users/login-error")
                                .param("username", "username")
                                .param("password", "testPassword")
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("user-login"));
    }

}
