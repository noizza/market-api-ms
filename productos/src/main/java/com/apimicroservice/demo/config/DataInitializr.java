package com.apimicroservice.demo.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apimicroservice.demo.model.Category;
import com.apimicroservice.demo.model.Product;
import com.apimicroservice.demo.model.Supplier;
import com.apimicroservice.demo.repository.CategoryRepository;
import com.apimicroservice.demo.repository.ProductRepository;
import com.apimicroservice.demo.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInitializr implements CommandLineRunner {
    private final ProductRepository prodRepo;
    private final CategoryRepository catRepo;
    private final SupplierRepository supRepo;

    @Override
    public void run(String... args) throws Exception {
        if(!catRepo.existsBy()) {
            List<Category> categoriesKiosco = List.of(
                Category.builder()
                    .name("Cat. General")
                    .description("Categoria para Mercaderias Generales")
                    .build(),
                Category.builder()
                    .name("Harinas")
                    .description("Hainas")
                    .build(),
                Category.builder()
                    .name("Alfajores")
                    .description("Alfajores")
                    .build(),
                Category.builder()
                    .name("Galletitas Dulces")
                    .description("Galletitas Dulces")
                    .build(),
                Category.builder()
                    .name("Copetin")
                    .description("Articulos de cumpleaños")
                    .build()
            );

            for(Category cat : categoriesKiosco) {
                catRepo.save(cat);
            }
        }

        if(!supRepo.existsBy()) {
            List<Supplier> suppliersKiosco = List.of(
                Supplier.builder()
                    .name("Proveedor General")
                    .cuit(00000000000)
                    .address("Direccion General")
                    .phone("-")
                    .build(),
                Supplier.builder()
                    .name("Super El Condor")
                    .cuit(00000000001)
                    .address("Apostoles Nte & R14")
                    .phone("-")
                    .build(),
                Supplier.builder()
                    .name("Dinco Supemercados")
                    .cuit(00000000002)
                    .address("Av. Sarmiento 925")
                    .phone("-")
                    .build()
            );

            for(Supplier sup : suppliersKiosco) {
                supRepo.save(sup);
            }
        }

        if(!prodRepo.existsBy()) { 
            Category galletitasDulces = catRepo.findByName("Galletitas Dulces").orElse(null);
            Category copetin = catRepo.findByName("Copetin").orElse(null);
            Category alfajores = catRepo.findByName("Alfajores").orElse(null);
            Supplier supGeneral = supRepo.findByCuit(00000000000).orElse(null);
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
                    .category(galletitasDulces)
                    .supplier(supGeneral)
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
                    .category(copetin)
                    .supplier(supGeneral)
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
                    .category(alfajores)
                    .supplier(supGeneral)
                    .createdAt(LocalDateTime.now())
                    .build()
            );

            for (Product producto : productosKiosco) {
                prodRepo.save(producto);
            }
        }
    }
}