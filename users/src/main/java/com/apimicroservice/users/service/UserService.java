package com.apimicroservice.users.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apimicroservice.users.dto.LoginRequestDTO;
import com.apimicroservice.users.dto.UserResponseDTO;
import com.apimicroservice.users.exception.BadCredentialsException;
import com.apimicroservice.users.model.User;
import com.apimicroservice.users.repository.UserMapper;
import com.apimicroservice.users.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.secret}")
    private String KEY_;

    private SecretKey getSingKey() {
        return Keys.hmacShaKeyFor(KEY_.getBytes(StandardCharsets.UTF_8));
    }

    public UserResponseDTO createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);
        return mapper.toDTO(savedUser);
    }

    public String login(LoginRequestDTO req) {
        User user = repository.findByName(req.username())
            .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        extraClaims.put("status", user.getStatus());

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (
                    user.getRole() == 5 || req.stayLogged() ?
                        (30L * 24 * 60 * 60 * 1000) : (1L * 24 * 60 * 60 * 1000)
                )))
                .signWith(getSingKey())
                .compact();
    }

}
