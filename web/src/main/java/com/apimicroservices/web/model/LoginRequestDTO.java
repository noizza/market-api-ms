package com.apimicroservices.web.model;

public record LoginRequestDTO(
    String username,
    String password,
    boolean stayLogged
) {}
