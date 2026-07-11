package com.apimicroservices.web.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apimicroservices.web.clients.productClient;
import com.apimicroservices.web.model.prProductDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class dashboardController {
    private final productClient productClient;

    @GetMapping("/")
    public String getDashboard(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";
        DecodedJWT decodedJWT = JWT.decode(token);
        model.addAttribute("username", decodedJWT.getSubject());
        model.addAttribute("products", Collections.emptyList());
        return "ventas/nueva-venta";
    }

    @GetMapping("/ventas/buscar-producto")
    public String getBuscarProductos(@RequestParam("text") String text, Model model, HttpSession session) {
        if(text == null || text.isEmpty()) {
            model.addAttribute("products", Collections.emptyList());
        } else {
            List<prProductDTO> products = productClient.searchProducts(text);
            model.addAttribute("products", products);
        }
        return "ventas/nueva-venta :: sugerencias-buscador";
    }
}
