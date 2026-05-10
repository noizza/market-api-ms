package com.apimicroservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apimicroservice.demo.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
