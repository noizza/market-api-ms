package com.apimicroservice.users.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimicroservice.users.dto.UserResponseDTO;
import com.apimicroservice.users.model.User;
import com.apimicroservice.users.service.UserService;
import com.apimicroservice.users.dto.LoginRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;

    @PostMapping("/register")
    public UserResponseDTO createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO req) {
        return service.login(req);
    }
}
