package com.apimicroservice.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apimicroservice.demo.model.Cliente;
import com.apimicroservice.demo.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInitializr implements CommandLineRunner {
    private final ClienteRepository repo;

    @Override
    public void run(String... args) throws Exception {
        if(!repo.existsBy()) {
            repo.save(new Cliente("Publico General", "xxxx@gmail.com"));
        }
    }
}
