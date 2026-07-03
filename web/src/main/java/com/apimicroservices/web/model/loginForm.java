package com.apimicroservices.web.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class loginForm {
    private String username;
    private String password;
}
