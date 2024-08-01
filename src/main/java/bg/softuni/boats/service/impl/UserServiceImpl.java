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
import bg.softuni.boats.service.UserService;
import bg.softuni.boats.service.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = modelMapper.map(userRegistrationDTO, UserEntity.class);
        user.setRole(List.of(userRoleRepository.findByRole(UserRoleEnum.USER)
                .orElseThrow(() -> new IllegalArgumentException("Role USER not found!"))));
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
        userRepository.save(user);

        applicationEventPublisher.publishEvent(new UserRegisterEvent(
                "UserService", userRegistrationDTO.username(), userRegistrationDTO.email()
        ));
    }

    @Override
    public UserViewModel findUserByUsername(String name) {
        UserEntity userEntity = userRepository.findByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("User with name " + name + " not found!"));

        UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);
        return userViewModel;
    }

    @Override
    public void updateUserProfile(UserProfileEditDTO userProfileEditDTO) {
        UserEntity userEntity = userRepository.findById(userProfileEditDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + userProfileEditDTO.getId() + " not found!"));
        userEntity.setFirstName(userProfileEditDTO.getFirstName())
                .setLastName(userProfileEditDTO.getLastName())
                .setEmail(userProfileEditDTO.getEmail());
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserWithRoleViewModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        return userRepository.findAll(pageable)
                .map(userEntity -> {
                    UserWithRoleViewModel userWithRoleViewModel = modelMapper.map(userEntity, UserWithRoleViewModel.class);
                    List<UserRoleEnum> roles = userEntity.getRole()
                            .stream()
                            .map(role -> UserRoleEnum.valueOf(role.getRole().name()))
                            .toList();
                    userWithRoleViewModel.setRole(roles);
                    return userWithRoleViewModel;
                });
    }

    @Override
    public void promoteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
        UserRoleEntity userRoleEntity = userRoleRepository.findByRole(UserRoleEnum.ADMIN)
                .orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found!"));
        userEntity.getRole().add(userRoleEntity);
        userRepository.save(userEntity);
    }

    @Override
    public void demoteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
        UserRoleEntity userRoleEntity = userRoleRepository.findByRole(UserRoleEnum.ADMIN)
                .orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found!"));
        userEntity.getRole().remove(userRoleEntity);
        userRepository.save(userEntity);
    }

    @Override
    public UserWithRoleViewModel findUserRoleByUsername(String name) {
        return userRepository.findByUsername(name)
                .map(userEntity -> {
                    UserWithRoleViewModel userWithRoleViewModel = modelMapper.map(userEntity, UserWithRoleViewModel.class);
                    List<UserRoleEnum> roles = userEntity.getRole()
                            .stream()
                            .map(role -> UserRoleEnum.valueOf(role.getRole().name()))
                            .toList();
                    userWithRoleViewModel.setRole(roles);
                    return userWithRoleViewModel;
                })
                .orElseThrow(() -> new UserNotFoundException("User with name " + name + " not found!"));
    }

    @Override
    public UserEntity findUserByUsernameEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with name " + username + " not found!"));
    }

    @Override
    public Optional<UserEntity> findByUsername(String value) {
        return userRepository.findByUsername(value);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String value) {
        return userRepository.findUserByEmail(value);
    }

    @Override
    public UserEntity getAdminEntity() {
        return userRepository.findByUsername("htaushanov")
                .orElseThrow(() -> new UserNotFoundException("Admin not found!"));
    }

    @Override
    public UserViewModel getCurrentUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with name " + username + " not found!"));
        return modelMapper.map(userEntity, UserViewModel.class);
    }


}














