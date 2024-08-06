package bg.softuni.boats.web;

import bg.softuni.boats.model.dto.UserProfileEditDTO;
import bg.softuni.boats.model.view.UserViewModel;
import bg.softuni.boats.model.view.UserWithRoleViewModel;
import bg.softuni.boats.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final String TEST_USER_OWNER_NAME = "userOwner";
    private static final String TEST_USER_NOT_OWNER_NAME = "userNotOwner";
    private static final String TEST_ADMIN_NAME = "admin";

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    void testViewProfile() throws Exception {
        UserViewModel userViewModel = new UserViewModel();
        when(userService.getCurrentUser(anyString())).thenReturn(userViewModel);

        mockMvc.perform(get("/users/view-profile").principal(() -> "user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile-view"))
                .andExpect(model().attributeExists("userViewModel"))
                .andExpect(model().attributeExists("currentUser"));

        verify(userService, times(1)).getCurrentUser("user");
    }

    @Test
    void testEditProfileGet() throws Exception {
        UserProfileEditDTO userProfileEditDTO = new UserProfileEditDTO();
//        when(userService.findUserByUsername(anyString())).thenReturn(userProfileEditDTO);

        mockMvc.perform(get("/users/profile/edit").principal(() -> "user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile-edit"));

        verify(userService, times(1)).findUserByUsername("user");
    }

    @Test
    void testDeActivate() throws Exception {
        mockMvc.perform(delete("/users/deactivate/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/all"));

        verify(userService, times(1)).deactivate(1L);
    }

    @Test
    void testActivate() throws Exception {
        mockMvc.perform(patch("/users/activate/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/all"));

        verify(userService, times(1)).activate(1L);
    }

    @Test
    void testGetOnePage() throws Exception {
        List<UserWithRoleViewModel> users = new ArrayList<>();
        Page<UserWithRoleViewModel> page = new PageImpl<>(users);
        when(userService.findPage(anyInt())).thenReturn(page);

        mockMvc.perform(get("/users/all/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-users-all"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("totalUsers"))
                .andExpect(model().attributeExists("users"));

        verify(userService, times(1)).findPage(1);
    }

    @Test
    void testPromoteUser() throws Exception {
        mockMvc.perform(post("/users/promote/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/all/1"));

        verify(userService, times(1)).promoteUser(1L);
    }

    @Test
    void testDemoteUser() throws Exception {
        mockMvc.perform(post("/users/demote/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/all/0"));

        verify(userService, times(1)).demoteUser(1L);
    }
    

}

