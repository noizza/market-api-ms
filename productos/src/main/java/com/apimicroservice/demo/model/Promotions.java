package com.apimicroservice.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @OneToMany(mappedBy = "promotion")
    private List<Product> products;
    private Integer promotionTypeId;
    /* Notas mentales para despues:
        * promotionTypeId:
        * 1: Percentage discount
        * 2: Buy X for X(2x1)
    */
    private Integer discountPercentage;
    private Integer buyX;
    private Integer getX;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
