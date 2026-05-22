package com.apimicroservice.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimicroservice.demo.dto.VentaDTO;
import com.apimicroservice.demo.dto.VentaRequestDTO;
import com.apimicroservice.demo.service.VentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService service;

    @PostMapping
    public ResponseEntity<VentaDTO> createVenta(@Valid @RequestBody VentaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createVenta(request));
    }
}
