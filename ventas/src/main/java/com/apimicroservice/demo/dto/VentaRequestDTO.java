package com.apimicroservice.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentaRequestDTO {
    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    private LocalDateTime date;

    @NotNull(message = "El metodo de pago es obligatorio")
    @Min(value = 1, message = "El metodo de pago debe ser un numero positivo")
    @Max(value = 4, message = "El metodo de pago debe ser un numero entre 1 y 4")
    private Integer paymentMethod;

    @NotEmpty(message = "La lista de detalles no puede estar vacía")
    private List<ItemRequestDTO> detalles;
}