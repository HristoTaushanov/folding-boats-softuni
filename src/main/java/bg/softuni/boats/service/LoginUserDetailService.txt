package bg.softuni.boats.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class LoginUserDetailService implements UserDetailsService {

//    private final UserRepository userRepository;

//    public LoginUserDetailService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("1")){
            return new User("", "", List.of());
        } else {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
//        return userRepository.findByUsername(username)
//                .map(LoginUserDetailService::map)
//                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));
    }

//    private static UserDetails map(UserEntity userEntity) {
//        return User
//                .withUsername(userEntity.getUsername())
//                .password(userEntity.getPassword())
//                .authorities(userEntity.getRole().stream().map(LoginUserDetailService::map).toList())
//                .build();
//    }
//
//    private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
//        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
//    }
}












