package com.apimicroservice.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apimicroservice.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByName(String name);

}
