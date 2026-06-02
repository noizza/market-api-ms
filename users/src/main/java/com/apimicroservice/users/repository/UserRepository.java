package com.apimicroservice.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apimicroservice.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
