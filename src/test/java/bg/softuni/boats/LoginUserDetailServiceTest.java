package bg.softuni.boats;

import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.model.entity.UserRoleEntity;
import bg.softuni.boats.model.enums.UserRoleEnum;
import bg.softuni.boats.repository.UserRepository;
import bg.softuni.boats.service.LoginUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static bg.softuni.boats.model.enums.UserRoleEnum.ADMIN;
import static bg.softuni.boats.model.enums.UserRoleEnum.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUserDetailServiceTest {

    private LoginUserDetailService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new LoginUserDetailService(mockUserRepository);
    }

    @Test
    void testMock() {
        UserEntity userEntity = new UserEntity().setUsername("test");
        when(mockUserRepository.findByUsername("test"))
                .thenReturn(Optional.of(userEntity));
        Optional<UserEntity> userOpt = mockUserRepository.findByUsername("test");

        UserEntity user = userOpt.get();
        assertEquals(user.getUsername(), userEntity.getUsername());
    }

    @Test
    void testUserNotFoundShouldThrow() {
        assertThrows(UsernameNotFoundException.class, () -> {
            serviceToTest.loadUserByUsername("testUsername");
        });
    }

    @Test
    void testUserFoundException() {
        UserEntity testUserEntity = new UserEntity();
        UserRoleEntity role_user = new UserRoleEntity().setRole(USER);
        UserRoleEntity role_admin = new UserRoleEntity().setRole(ADMIN);
        testUserEntity.setUsername("testUsername")
                .setPassword("testPassword")
                .setRole(List.of(role_user, role_admin));

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));


        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        assertNotNull(userDetails);
        assertEquals(testUserEntity.getUsername(), userDetails.getUsername(), "Username should match");
        assertEquals(testUserEntity.getPassword(), userDetails.getPassword(), "Password should match");
        assertEquals(2, userDetails.getAuthorities().size(), "Number of roles should match");
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN.name()), "The user user is not admin");
        assertTrue(containsAuthority(userDetails, "ROLE_" + USER.name()), "The user user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority){
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a->a.getAuthority().equals(expectedAuthority));
    }



}
