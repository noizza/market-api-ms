package com.apimicroservice.demo.controller;

import java.util.List;
import java.util.Map;

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
import com.apimicroservice.demo.dto.prProductDTO;
import com.apimicroservice.demo.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/productos")
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
    
    @GetMapping("/{barcode}")
    public ProductDTO getProduct(@PathVariable Long barcode) {
        return service.getProductDTOByBarcode(barcode);
    }

    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long barcode) {
        service.deleteProductByBarcode(barcode);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ProductDTO updateProduct(@Valid @RequestBody ProductDTO dto) {
        return service.updateProductDTO(dto);
    }

    @GetMapping("/search")
    public List<prProductDTO> searchProducts(@RequestParam String name) {
        return service.searchProductsByNameDTO(name);
    }

    @PostMapping("/{barcode}/reduce-stock")
    public ResponseEntity<Void> reduceStock(@PathVariable Long barcode, @RequestParam Double quantity) {
        service.reduceStock(barcode, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ProductDTO>> getProductsBatch(@RequestBody List<Long> barcodes) {
        List<ProductDTO> products = service.getProductsByBarcodeBatch(barcodes); 
        return ResponseEntity.ok(products);
    }

    @PostMapping("/batch/reduce-stock")
    public ResponseEntity<Void> reduceStockBatch(@RequestBody Map<Long, Integer> stockReductions) {
        service.reduceStockBatch(stockReductions);
        return ResponseEntity.ok().build();
    }
}
