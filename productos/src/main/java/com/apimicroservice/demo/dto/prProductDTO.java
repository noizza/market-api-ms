package com.apimicroservice.demo.dto;

//pr = Public Response

import java.util.List;

public record prProductDTO(
    Long barcode,
    String name,
    String description,
    Double price,
    Integer stock,
    Long categoryId,
    boolean active,
    boolean promoted,
    Long promotionId,
    List<prProductSpecs> specs
) {
    
}
