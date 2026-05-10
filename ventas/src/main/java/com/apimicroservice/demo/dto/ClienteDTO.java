package com.apimicroservice.demo.dto;

public record ClienteDTO(
    Long id,
    String nombre,
    String email
) {
}