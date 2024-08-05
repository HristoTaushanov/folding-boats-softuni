package bg.softuni.boats.service.impl;

import bg.softuni.boats.model.dto.UserProfileEditDTO;
import bg.softuni.boats.model.dto.UserRegistrationDTO;
import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.model.entity.UserRoleEntity;
import bg.softuni.boats.model.enums.UserRoleEnum;
import bg.softuni.boats.model.event.UserRegisterEvent;
import bg.softuni.boats.model.view.UserViewModel;
import bg.softuni.boats.model.view.UserWithRoleViewModel;
import bg.softuni.boats.repository.UserRepository;
import bg.softuni.boats.repository.UserRoleRepository;
import bg.softuni.boats.service.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserRegistrationDTO userRegistrationDTO =
                new UserRegistrationDTO("john_doe", "john@example.com", "password123",
                        "password123", "John", "Doe");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");
        userEntity.setEmail("john@example.com");

        when(modelMapper.map(userRegistrationDTO, UserEntity.class)).thenReturn(userEntity);
        when(userRoleRepository.findByRole(UserRoleEnum.USER)).thenReturn(Optional.of(new UserRoleEntity(UserRoleEnum.USER)));
        when(passwordEncoder.encode(userRegistrationDTO.password())).thenReturn("encodedPassword");

        userService.registerUser(userRegistrationDTO);

        verify(userRepository, times(1)).save(userEntity);
        verify(applicationEventPublisher, times(1)).publishEvent(any(UserRegisterEvent.class));
    }

    @Test
    public void testFindUserByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");
        userEntity.setEmail("john@example.com");

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setUsername("john_doe");
        userViewModel.setEmail("john@example.com");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, UserViewModel.class)).thenReturn(userViewModel);

        UserViewModel result = userService.findUserByUsername("john_doe");

        assertEquals(userViewModel, result);
    }

    @Test
    public void testFindUserByUsernameNotFound() {
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserByUsername("john_doe"));
    }

    @Test
    public void testUpdateUserProfile() {
        UserProfileEditDTO userProfileEditDTO = new UserProfileEditDTO();
        userProfileEditDTO.setId(1L);
        userProfileEditDTO.setFirstName("John");
        userProfileEditDTO.setLastName("Doe");
        userProfileEditDTO.setEmail("testME@abv.bg");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        userService.updateUserProfile(userProfileEditDTO);

        assertEquals("John", userEntity.getFirstName());
        assertEquals("Doe", userEntity.getLastName());
        assertEquals("testME@abv.bg", userEntity.getEmail());
        assertEquals(1L, userEntity.getId());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testFindPage() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");
//        userEntity.setRole(List.of(new UserRoleEntity(UserRoleEnum.USER)));
        userEntity.setRole(new ArrayList<>());
        UserWithRoleViewModel userWithRoleViewModel = new UserWithRoleViewModel();
        userWithRoleViewModel.setUsername("john_doe");

        Page<UserEntity> userPage = new PageImpl<>(List.of(userEntity));

        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);
        when(modelMapper.map(userEntity, UserWithRoleViewModel.class)).thenReturn(userWithRoleViewModel);

        Page<UserWithRoleViewModel> result = userService.findPage(1);

        assertEquals(1, result.getTotalElements());
        assertEquals("john_doe", result.getContent().get(0).getUsername());
    }

    @Test
    public void testPromoteUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setRole(new ArrayList<>());
        userEntity.setRole(new ArrayList<>(List.of(new UserRoleEntity(UserRoleEnum.USER))));

        UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(Optional.of(adminRole));

        userService.promoteUser(1L);

        assertEquals(2, userEntity.getRole().size());
        assertEquals(UserRoleEnum.ADMIN, userEntity.getRole().get(1).getRole());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testDemoteUser() {
        UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setRole(new ArrayList<>(List.of(adminRole)));

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        userService.demoteUser(1L);

        assertEquals(0, userEntity.getRole().size());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testFindUserRoleByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");
        userEntity.setRole(new ArrayList<>());
        UserWithRoleViewModel userWithRoleViewModel = new UserWithRoleViewModel();
        userWithRoleViewModel.setUsername("john_doe");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, UserWithRoleViewModel.class)).thenReturn(userWithRoleViewModel);

        UserWithRoleViewModel result = userService.findUserRoleByUsername("john_doe");

        assertEquals(userWithRoleViewModel, result);
    }

    @Test
    public void testFindUserRoleByUsernameNotFound() {
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserRoleByUsername("john_doe"));
    }

    @Test
    public void testFindUserByUsernameEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.findUserByUsernameEntity("john_doe");

        assertEquals(userEntity, result);
    }

    @Test
    public void testFindUserByUsernameEntityNotFound() {
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserByUsernameEntity("john_doe"));
    }

    @Test
    public void testFindByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userService.findByUsername("john_doe");

        assertEquals(Optional.of(userEntity), result);
    }

    @Test
    public void testFindUserByEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("john@example.com");

        when(userRepository.findUserByEmail("john@example.com")).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userService.findUserByEmail("john@example.com");

        assertEquals(Optional.of(userEntity), result);
    }

    @Test
    public void testGetAdminEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("htaushanov");

        when(userRepository.findByUsername("htaushanov")).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.getAdminEntity();

        assertEquals(userEntity, result);
    }

    @Test
    public void testGetAdminEntityNotFound() {
        when(userRepository.findByUsername("htaushanov")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getAdminEntity());
    }

    @Test
    public void testGetCurrentUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john_doe");

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setUsername("john_doe");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, UserViewModel.class)).thenReturn(userViewModel);

        UserViewModel result = userService.getCurrentUser("john_doe");

        assertEquals(userViewModel, result);
    }

    @Test
    public void testGetCurrentUserNotFound() {
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getCurrentUser("john_doe"));
    }

    @Test
    public void testGetUserEntityById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.getUserEntityById(1L);

        assertEquals(userEntity, result);
    }

    @Test
    public void testGetUserEntityByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserEntityById(1L));
    }
}

