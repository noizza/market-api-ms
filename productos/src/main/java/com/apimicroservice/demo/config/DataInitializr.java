package com.apimicroservice.demo.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apimicroservice.demo.model.Product;
import com.apimicroservice.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInitializr implements CommandLineRunner {
    private final ProductRepository repo;

    @Override
    public void run(String... args) throws Exception {
        if(!repo.existsBy()) { 
            List<Product> productosKiosco = List.of(
                Product.builder()
                    .barcode(7791234567891L)
                    .name("Galletitas Sonrisas Bagley 118g")
                    .description("Galletitas dulces rellenas con sabor a frambuesa.")
                    .cost(800.00)
                    .price(1100.00)
                    .stock(30)
                    .minStock(5)
                    .taxesIncluded(true)
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .build(),

                Product.builder()
                    .barcode(7791234567892L)
                    .name("Palitos Salados Pehuamar 65g")
                    .description("Snack clásico salado ideal para copetín.")
                    .cost(950.00)
                    .price(1350.00)
                    .stock(20)
                    .minStock(4)
                    .taxesIncluded(true)
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .build(),

                Product.builder()
                    .barcode(7791234567893L)
                    .name("Alfajor Guaymallén Chocolate")
                    .description("Alfajor triple relleno con dulce de leche.")
                    .cost(300.00)
                    .price(450.00)
                    .stock(50)
                    .minStock(10)
                    .taxesIncluded(true)
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .build()
            );

            for (Product producto : productosKiosco) {
                repo.save(producto);
            }
        }
    }
}