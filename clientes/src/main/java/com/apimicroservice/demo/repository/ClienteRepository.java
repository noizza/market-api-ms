package com.apimicroservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apimicroservice.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
