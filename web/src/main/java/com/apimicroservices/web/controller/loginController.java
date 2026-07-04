package com.apimicroservices.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apimicroservices.web.model.loginForm;
import com.apimicroservices.web.service.loginService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class loginController {
    private final loginService service;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new loginForm());
        model.addAttribute("error", null);
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(loginForm loginForm, RedirectAttributes redirectAttributes, Model model) {
        return service.login(loginForm, redirectAttributes, model);
    }
}
