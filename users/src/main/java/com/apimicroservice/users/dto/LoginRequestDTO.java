package com.apimicroservice.users.dto;

import jakarta.validation.constraints.NotBlank;


public record LoginRequestDTO(
    @NotBlank(message = "Debes poner un nombe de usuario.")
    String name,
    @NotBlank(message = "Debes escribir tu contaseña.")
    String pass,

    Boolean stayLogged
) {
    
}
