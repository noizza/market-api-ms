package com.apimicroservice.demo.dto;

import lombok.Data;

@Data
public class ItemRequestDTO {
    private Long productoId;
    private Double cantidad;
}