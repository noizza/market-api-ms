package com.apimicroservice.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apimicroservice.demo.dto.ProductDTO;
import com.apimicroservice.demo.dto.prProductDTO;
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
    public ProductDTO getProductDTOByBarcode(Long barcode) {
        var product = repository.findByBarcode(barcode).orElseThrow(() -> new ResourceNotFoundException("El producto con código de barras " + barcode + " no existe."));
        return mapper.toDTO(product);
    }

    public ProductDTO updateProductDTO(ProductDTO dto) {
         var product = repository.findByBarcode(dto.barcode()).orElseThrow(() -> new ResourceNotFoundException("El producto con código de barras " + dto.barcode() + " no existe."));
        mapper.updateProductFromDTO(dto, product);
        var updatedProduct = repository.save(product);
        return mapper.toDTO(updatedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        var products = repository.findAll();
        return mapper.toDTOList(products);
    }

    public void deleteProductByBarcode(Long barcode) {
        var product = repository.findByBarcode(barcode)
            .orElseThrow(() -> new ResourceNotFoundException("El producto con código de barras " + barcode + " no existe."));
        repository.delete(product);
    }

    @Transactional(readOnly = true)
    public List<prProductDTO> searchProductsByNameDTO(String name) {
        return mapper.toPrDTOList(repository.findByNameContainingIgnoreCase(name));
    }

    @Transactional
    public void reduceStock(Long barcode, Double quantity) {
        var product = repository.findByBarcode(barcode).orElseThrow(() -> new ResourceNotFoundException("El producto con código de barras " + barcode + " no existe."));
        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
        }
        product.setStock(product.getStock() - quantity.intValue());
        repository.save(product);
    }

    @Transactional
    public void reduceStockBatch(Map<Long, Integer> entry) {
        entry.forEach((barcode, quantity) -> {
            int updateRows = repository.reduceStockByBarcode(barcode, quantity);
            if(updateRows == 0) {
                throw new RuntimeException("Stock insuficiente para el producto con código de barras: " + barcode);
            }
        });
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByBarcodeBatch(List<Long> barcodes) {
    if(barcodes == null || barcodes.isEmpty()) {
        throw new IllegalArgumentException("La lista de códigos de barras no puede estar vacía.");
    }
    var products = repository.findByBarcodeIn(barcodes); 
    return mapper.toDTOList(products);
}

    public List<ProductDTO> searchProductsContainsNameDTO(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
