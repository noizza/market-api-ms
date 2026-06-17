package com.apimicroservice.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<ProductSpecs> specs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplierId;


    private boolean active;
    private boolean periodic;
    private LocalDateTime createdAt;
    private LocalDateTime inventoryUpdatedAt;
    private boolean promoted;
    private Promotions promotionId;
}
