package com.apimicroservice.demo.dto;

public record ProductoDTO(
    Long barcode,
    String name,
    Double price,
    Integer stock
) {

}
