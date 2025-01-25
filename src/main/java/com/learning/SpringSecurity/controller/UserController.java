package com.learning.SpringSecurity.controller;

import com.learning.SpringSecurity.entity.User;
import com.learning.SpringSecurity.repository.UserRepository;
import com.learning.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
//        return userRepository.save(user);
        return userService.register(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return userService.verifyUser(user);


    }
}
