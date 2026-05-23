package com.apimicroservice.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.exception.ResourceNotFoundException;
import com.apimicroservice.demo.model.Cliente;
import com.apimicroservice.demo.repository.ClienteMapper;
import com.apimicroservice.demo.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteDTO createClienteDTO(ClienteDTO dto) {
        Cliente cliente = mapper.toEntity(dto);
        Cliente savedCliente = repository.save(cliente);
        return mapper.toDTO(savedCliente);
    }

    public ClienteDTO getClienteDTOById(Long id) {
        Cliente c = repository.findById(id).orElseThrow(()
            -> new ResourceNotFoundException("El cliente con ID " + id + " no existe."));
        return mapper.toDTO(c);
    }

    public List<ClienteDTO> getAllClientes() {
        List<Cliente> all = repository.findAll();
        return mapper.toDTOList(all);
    }
}
