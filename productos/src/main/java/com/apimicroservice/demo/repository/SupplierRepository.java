package com.apimicroservice.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apimicroservice.demo.model.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByCuit(Integer cuit);
    boolean existsBy();
}
