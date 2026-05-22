package com.apimicroservice.demo.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.apimicroservice.demo.dto.ClienteDTO;
import com.apimicroservice.demo.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> all);
}
