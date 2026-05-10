package com.apimicroservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.service.ClienteService;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    
    @PostMapping
    public ClienteDTO createCliente(@RequestBody ClienteDTO dto) {
        return service.createClienteDTO(dto);
    }

    @GetMapping
    public Iterable<ClienteDTO> getAllClientes() {
        return service.getAllClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO getCliente(@PathVariable Long id) {
        return service.getClienteDTOById(id);
    }
}
