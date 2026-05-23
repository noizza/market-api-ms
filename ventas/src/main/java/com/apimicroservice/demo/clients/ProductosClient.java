package com.apimicroservice.demo.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.apimicroservice.demo.dto.ProductoDTO;

@FeignClient(name = "ms-productos")
public interface ProductosClient {
    @GetMapping("/{id}")
    ProductoDTO getProductoById(@PathVariable Long id);

    @GetMapping("/search/{name}")
    List<ProductoDTO> searchProductosByName(@PathVariable String name);

    @PostMapping("/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id, Double quantity);

    @PostMapping("/batch")
    List<ProductoDTO> getProductosBatch(List<Long> ids);

    @PostMapping("/batch/reduce-stock")
    void reduceStockBatch(List<Long> productIds, List<Double> quantities);
}
