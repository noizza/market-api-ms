package com.apimicroservice.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UserResponseDTO(
    @PositiveOrZero(message = "El ID debe ser positivo")
    Long id,

    @NotBlank(message = "Debes ingresar un nombre.")
    String name,

    @Email(message = "Fomato de email invalido.")
    String email,

    @Min(value = 0, message = "Numero de Rol Invalido. Minimo: min.value")
    @Max(value = 5, message = "Numero de Rol Invalido. Maximo: max.value")
    Integer role,


    Boolean status
) {

}
