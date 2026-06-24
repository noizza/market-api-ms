package com.apimicroservice.demo.clients;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.apimicroservice.demo.dto.ProductoDTO;

@FeignClient(name = "ms-productos", path = "/api/productos")
public interface ProductosClient {
    @GetMapping("/{barcode}")
    ProductoDTO getProductoByBarcode(@PathVariable Long barcode);

    @GetMapping("/search/{name}")
    List<ProductoDTO> searchProductosByName(@PathVariable String name);

    @PostMapping("/{barcode}/reduce-stock")
    void reduceStock(@PathVariable Long barcode, Double quantity);

    @PostMapping("/batch")
    List<ProductoDTO> getProductosBatch(@RequestBody List<Long> ids);

    @PostMapping("/batch/reduce-stock")
    void reduceStockBatch(@RequestBody Map<Long, Integer> stockReductions);
}
