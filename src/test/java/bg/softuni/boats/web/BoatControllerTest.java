package bg.softuni.boats.web;

import bg.softuni.boats.model.dto.BoatDTO;
import bg.softuni.boats.service.BoatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoatControllerTest {

    private MockMvc mockMvc;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @MockBean
    private BoatService boatService;


    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddBoat() throws Exception {
        mockMvc.perform(
                        get("/boat/add")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("boat-add"));
    }

    @Test
    void testAddBoatAccessDenied() throws Exception {
        mockMvc.perform(
                        get("/boat/add")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("boat-add"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddBoatPost() throws Exception {
        mockMvc.perform(
                        get("/boat/add")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("boat-add"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testAddBoatPostAccessDenied() throws Exception {
        mockMvc.perform(
                        get("/boat/add")
                )
                .andExpect(status().isOk());
    }

    @Test
    void testDetailNotLogIn() throws Exception {
        mockMvc.perform(
                        get("/boat/detail/{id}", 1)
                )
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDetailLogIn() throws Exception {
        mockMvc.perform(
                        get("/boat/detail/{id}", 1)
                )
                .andExpect(status().isOk());
    }

    @Test
    void testAddCommentNoUser() throws Exception {
        mockMvc.perform(
                        get("/boat/add-comment/{id}", 1)
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddCommentWithUser() throws Exception {
        mockMvc.perform(
                        get("/boat/add-comment/{id}", 1)
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddBoatWithoutErrors() throws Exception {
        mockMvc.perform(post("/add")
                        .param("name", "Boaty McBoatface")
                        .param("type", "Sailboat")
                        .param("length", "30"))
                .andExpect(status().isOk());
    }

}
