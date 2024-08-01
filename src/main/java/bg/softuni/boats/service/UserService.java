package bg.softuni.boats.service;

import bg.softuni.boats.model.dto.UserProfileEditDTO;
import bg.softuni.boats.model.dto.UserRegistrationDTO;
import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.model.view.UserViewModel;
import bg.softuni.boats.model.view.UserWithRoleViewModel;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    UserViewModel findUserByUsername(String name);

    void updateUserProfile(UserProfileEditDTO userProfileEditDTO);

    void deleteUser(Long id);

    Page<UserWithRoleViewModel> findPage(int pageNumber);

    void promoteUser(Long id);

    void demoteUser(Long id);

    UserWithRoleViewModel findUserRoleByUsername(String name);

    UserEntity findUserByUsernameEntity(String username);

    Optional<UserEntity> findByUsername(String value);

    Optional<UserEntity> findUserByEmail(String value);

    UserEntity getAdminEntity();

    UserViewModel getCurrentUser(String username);
}
