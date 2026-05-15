package com.apimicroservice.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
//
public record ProductDTO(
    Long id,

    @NotBlank(message = "El nombre no puede estar vacío")
    String name,
    String description,
    @Min(value = 1, message = "El precio no puede ser negativo o cero")
    Double price,
    @Min(value = 1, message = "El stock no puede ser negativo o cero")
    Double stock,
    @Min(value = 1, message = "El stock mínimo no puede ser negativo o cero")
    Integer minStock
) {

}
