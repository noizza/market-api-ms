package com.apimicroservice.demo.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;
    private String name;
    private String description;
    private Double cost;
    private Double price;
    private Integer stock;
    private Integer unitId;
    private Integer minStock;
    private Integer maxStock;
    private Integer loyaltyPoints;
    private boolean TaxesIncluded;
    private Double taxRate;
    private Integer taxConditionId;
    
}
