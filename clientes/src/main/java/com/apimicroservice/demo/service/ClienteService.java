package com.apimicroservice.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.model.Cliente;
import com.apimicroservice.demo.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public ClienteDTO createClienteDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setName(dto.name());
        cliente.setEmail(dto.email());
        Cliente savedCliente = repository.save(cliente);
        return new ClienteDTO(savedCliente.getId(), savedCliente.getName(), savedCliente.getEmail());
    }

    public ClienteDTO getClienteDTOById(Long id) {
        Cliente c = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente not found"));
        return new ClienteDTO(c.getId(), c.getName(), c.getEmail());
    }

    public Iterable<ClienteDTO> getAllClientes() {
        return repository.findAll().stream()
                .map(c -> new ClienteDTO(c.getId(), c.getName(), c.getEmail()))
                .collect(Collectors.toList());
    }
}
