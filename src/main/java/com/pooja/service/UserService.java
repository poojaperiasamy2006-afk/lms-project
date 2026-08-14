package com.pooja.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pooja.lms.model.User;
import com.pooja.lms.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Find user by email (for login)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
