package com.apimicroservice.users.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "Debes poner un nombre de usuario.")
    String username,
    @NotBlank(message = "Debes escribir tu contraseña.")
    String password,
    Boolean stayLogged
) {}
