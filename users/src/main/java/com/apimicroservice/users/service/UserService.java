package com.apimicroservice.users.service;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apimicroservice.users.dto.UserResponseDTO;
import com.apimicroservice.users.model.User;
import com.apimicroservice.users.repository.UserMapper;
import com.apimicroservice.users.repository.UserRepository;

import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private UserRepository repository;
    private UserMapper mapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String KEY_ = "asdasd";

    private SecretKey getSingKey() {
        return Keys.hmacShaKeyFor(KEY_.getBytes(StandardCharsets.UTF_8));
    }

    public UserResponseDTO createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDTO(user);
    }

}
