package com.apimicroservice.demo.dto;

public record DetalleVentaDTO(
    Long id,
    Long productoId,
    Double cantidad,
    Double precioUnitario
) {}