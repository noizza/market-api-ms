package com.apimicroservice.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ItemRequestDTO {
    @NotEmpty(message = "El ID del producto es obligatorio")
    private Long productoId;
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Double cantidad;
}