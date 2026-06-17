package com.apimicroservice.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductDTO(
    Long id,

    String barcode,

    @NotBlank(message = "El nombre no puede estar vacío")
    String name,
    
    String description,
    
    @NotNull(message = "El costo es obligatorio")
    @PositiveOrZero(message = "El costo no puede ser negativo")
    Double cost,

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    Double price,

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    Integer stock,

    Integer unitId,

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    Integer minStock,

    @Min(value = 0, message = "El stock máximo no puede ser negativo")
    Integer maxStock,

    Integer loyaltyPoints,
    boolean taxesIncluded,
    Double taxRate,
    Integer taxConditionId,

    Long categoryId,
    Long supplierId,
    Long promotionId,

    boolean active,
    boolean periodic,
    LocalDateTime createdAt,
    LocalDateTime inventoryUpdatedAt,
    boolean promoted
    
) {
    //TODO: Agregar los otros DTOS que me faltaron.
}