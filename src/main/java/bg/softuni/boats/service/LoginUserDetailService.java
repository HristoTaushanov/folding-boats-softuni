package bg.softuni.boats.service;

import bg.softuni.boats.model.entity.UserEntity;
import bg.softuni.boats.model.entity.UserRoleEntity;
import bg.softuni.boats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
public class LoginUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public LoginUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(LoginUserDetailService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRole().stream().map(LoginUserDetailService::map).toList())
                .build();
    }

    private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
   /* @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("1")) {
            return new User("1", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode("1"), List.of());
        } else {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
    }*/

}
