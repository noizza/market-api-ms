package com.apimicroservice.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apimicroservice.demo.dto.ProductDTO;
import com.apimicroservice.demo.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(201).body(service.createProductDTO(dto));
    }

    @GetMapping
    public List<ProductDTO> getAllProduct() {
        return service.getAllProducts();
    }
    
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return service.getProductDTOById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ProductDTO updateProduct(@Valid @RequestBody ProductDTO dto) {
        return service.updateProductDTO(dto);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(@RequestParam String name) {
        return service.searchProductsByNameDTO(name);
    }
}
