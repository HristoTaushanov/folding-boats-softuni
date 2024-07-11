package bg.softuni.boats.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.List;

public class LoginUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("1")) {
            return new User("1", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode("1"), List.of());
        } else {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
    }
}
