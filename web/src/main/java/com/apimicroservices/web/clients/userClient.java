package com.apimicroservices.web.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.apimicroservices.web.model.LoginRequestDTO;

@FeignClient(name = "ms-usuarios")
public interface userClient {
    @PostMapping("/api/users/login")
    String login(@RequestBody LoginRequestDTO req);
}
