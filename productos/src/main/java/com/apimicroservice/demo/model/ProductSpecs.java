package com.apimicroservice.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_specs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecs {
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Long productId;
    
    private String color;
    private String size;
    private String brand;
    private String model;
}
