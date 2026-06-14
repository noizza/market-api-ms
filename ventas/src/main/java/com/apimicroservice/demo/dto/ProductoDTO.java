package com.apimicroservice.demo.dto;

public record ProductoDTO(
    Long id,
    String name,
    Double price,
    Double stock
) {

}
