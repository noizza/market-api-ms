package com.apimicroservice.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apimicroservice.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByNameContainingIgnoreCase(String name);

    @Modifying
    @Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :id AND p.stock >= :quantity")
    public int updateStock(@Param("id") Long id, @Param("quantity") Double quantity);
}
