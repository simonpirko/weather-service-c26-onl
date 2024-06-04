package by.tms.weatherservicec26onl.service;

import by.tms.weatherservicec26onl.entity.User;
import by.tms.weatherservicec26onl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User add(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsernameAndPassword(String username, String password) {

        Optional<User> byUsername = userRepository.findByUsername(username);
        System.out.println(username);
        System.out.println(password);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
