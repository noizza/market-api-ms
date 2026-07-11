package com.apimicroservices.web.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apimicroservices.web.model.prProductDTO;

@FeignClient(name = "ms-productos")
public interface productClient {
    @GetMapping("/api/productos/search")
    List<prProductDTO> searchProducts(@RequestParam("name") String text);
}
