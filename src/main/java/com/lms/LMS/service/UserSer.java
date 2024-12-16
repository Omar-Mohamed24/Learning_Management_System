package com.lms.LMS.service;
import com.lms.LMS.exception.NotFound;
import com.lms.LMS.model.User;
import com.lms.LMS.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserSer implements UserDetailsService {
    private final UserRepo repository;
    private final PasswordEncoder encoder;

    public UserSer(UserRepo repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByusername(username);
        return userDetail.map(CustomDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return repository.save(user);
    }

    public User GetUser(long userId) {
        return repository.findById(userId).orElseThrow(() -> new NotFound("user not found: " + userId));
    }

    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        return new ArrayList<>(users);
    }

    public User updateUser(long userId, User updated) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFound("user not found: " + userId));

        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setEmail(updated.getEmail());
        user.setPassword(updated.getPassword());
        user.setUsername(updated.getUsername());

        return repository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFound("user not found: " + userId));
        repository.deleteById(userId);
    }
}
