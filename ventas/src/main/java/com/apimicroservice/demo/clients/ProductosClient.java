package com.apimicroservice.demo.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apimicroservice.demo.dto.ProductoDTO;

@FeignClient(name = "ms-productos", url = "localhost:8082/api/productos")
public interface ProductosClient {
    @GetMapping("/{id}")
    ProductoDTO getProductoById(@PathVariable Long id);

    @GetMapping("/search/{name}")
    List<ProductoDTO> searchProductosByName(@PathVariable String name);
}
