package com.apimicroservice.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.apimicroservice.demo.dto.ProductDTO;
import com.apimicroservice.demo.model.Product;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getMinStock()
        );
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setId(dto.id());
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setMinStock(dto.minStock());
        return product;
    }

    public List<ProductDTO> toDTOList(List<Product> products) {
        if (products == null) return null;
        return products.stream()
                       .map(this::toDTO)
                       .collect(Collectors.toList());
    }

    public void updateProductFromDTO(ProductDTO dto, Product product) {
        if (dto == null || product == null) return;
        // No actualizamos el ID
        if(dto.name() != null) product.setName(dto.name());
        if(dto.description() != null) product.setDescription(dto.description());
        if(dto.price() != null) product.setPrice(dto.price());
        if(dto.stock() != null) product.setStock(dto.stock());
        if(dto.minStock() != null) product.setMinStock(dto.minStock());
    }
}