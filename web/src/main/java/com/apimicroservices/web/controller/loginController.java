package com.apimicroservices.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.apimicroservices.web.model.loginForm;
import com.apimicroservices.web.service.loginService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class loginController {
    private final loginService service;
    
    @GetMapping("/login")
    public String login(Model model, HttpSession session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("token") != null) return "redirect:/";
        model.addAttribute("loginForm", new loginForm());
        model.addAttribute("error", null);
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(loginForm loginForm, Model model, HttpSession session) {
        return service.login(loginForm, model, session);
    }
}
