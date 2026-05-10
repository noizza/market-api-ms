package com.apimicroservice.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class VentaRequestDTO {
    private Long id;
    private Long clienteId;
    private LocalDateTime date;
    private List<ItemRequestDTO> detalles;
}