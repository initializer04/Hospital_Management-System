package com.deepakcode.hello.controller;

import com.deepakcode.hello.entity.User;
import com.deepakcode.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user (Admin can add users)
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Get all users (Only admin should access this)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

