package com.apimicroservice.users.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apimicroservice.users.repository.UserMapper;
import com.apimicroservice.users.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private UserRepository repository;
    private UserMapper mapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

}
