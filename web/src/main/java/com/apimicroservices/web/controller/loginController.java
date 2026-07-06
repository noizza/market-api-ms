package com.apimicroservices.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.apimicroservices.web.model.loginForm;
import com.apimicroservices.web.service.loginService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class loginController {
    private final loginService service;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";
        DecodedJWT decodedJWT = JWT.decode(token);
        model.addAttribute("username", decodedJWT.getSubject());
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new loginForm());
        model.addAttribute("error", null);
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(loginForm loginForm, Model model, HttpSession session) {
        return service.login(loginForm, model, session);
    }
}
