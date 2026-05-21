package com.apimicroservice.demo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apimicroservice.demo.dto.ClienteDTO;

@FeignClient(name = "ms-cliente")
public interface ClienteClient {
    @GetMapping("/{id}")
    ClienteDTO getClienteById(@PathVariable Long id);
}
