package com.apimicroservice.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apimicroservice.demo.dto.ProductDTO;
import com.apimicroservice.demo.exception.ResourceNotFoundException;
import com.apimicroservice.demo.repository.ProductMapper;
import com.apimicroservice.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductDTO createProductDTO(ProductDTO dto) {
        var product = mapper.toEntity(dto);
        var savedProduct = repository.save(product);
        return mapper.toDTO(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductDTOById(Long id) {
        var product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + id + " no existe."));
        return mapper.toDTO(product);
    }

    public ProductDTO updateProductDTO(ProductDTO dto) {
         var product = repository.findById(dto.id()).orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + dto.id() + " no existe."));
        mapper.updateProductFromDTO(dto, product);
        var updatedProduct = repository.save(product);
        return mapper.toDTO(updatedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        var products = repository.findAll();
        return mapper.toDTOList(products);
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> searchProductsByNameDTO(String name) {
        return mapper.toDTOList(repository.findByNameContainingIgnoreCase(name));
    }

    @Transactional
    public void reduceStock(Long productId, Double quantity) {
        var product = repository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("El producto con ID " + productId + " no existe."));
        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
        }
        product.setStock(product.getStock() - quantity.intValue());
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> createProductsBatch(List<Long> ids) {
        if(ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("La lista de IDs no puede estar vacía.");
        }
        var products = repository.findAllById(ids);
        return mapper.toDTOList(products);
    }

    @Transactional
    public void reduceStockBatch(Map<Long, Double> entry) {
        for (Map.Entry<Long, Double> e : entry.entrySet()) {
            int updatedRows = repository.updateStock(e.getKey(), e.getValue());
            if (updatedRows == 0) {
                throw new RuntimeException("Stock insuficiente para el producto con ID: " + e.getKey());
            }
        }
    }
}
