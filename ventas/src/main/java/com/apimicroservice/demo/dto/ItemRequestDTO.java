package com.apimicroservice.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(
    @NotNull(message = "El codigo de barra del producto es obligatorio")
    Long barcode,
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    double cantidad
) {
    
}