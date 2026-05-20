package com.apimicroservice.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentaRequestDTO {
    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    private LocalDateTime date;

    @NotEmpty(message = "La lista de detalles no puede estar vacía")
    private List<ItemRequestDTO> detalles;
}