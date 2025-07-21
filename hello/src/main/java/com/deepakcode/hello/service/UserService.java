package com.deepakcode.hello.service;

import com.deepakcode.hello.entity.User;
import com.deepakcode.hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    // Get all users (for admin)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
