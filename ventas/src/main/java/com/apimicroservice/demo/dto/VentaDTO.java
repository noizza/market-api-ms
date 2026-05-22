package com.apimicroservice.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public record VentaDTO(
    Long id,
    Long clienteId,
    Double total_amount,
    LocalDateTime date,
    List<DetalleVentaDTO> detalles
) {}