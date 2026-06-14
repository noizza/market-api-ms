package com.apimicroservice.demo.config;

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
                new Product(null, "Galletitas Sonrisas Bagley 118g", "Galletitas dulces rellenas con sabor a frambuesa.", 1100.00, 30.0, 5),
                new Product(null, "Palitos Salados Pehuamar 65g", "Snack clásico salado ideal para copetín.", 1350.00, 20.0, 4),
                new Product(null, "Alfajor Guaymallén Chocolate", "Alfajor triple relleno con dulce de leche.", 450.00, 50.0, 10),
                new Product(null, "Gaseosa Coca-Cola Original 500ml", "Bebida sin alcohol en botella de plástico (PET).", 1600.00, 24.0, 6)
            );

            for (Product producto : productosKiosco) {
                    repo.save(producto);
            }
        }
    }
}
