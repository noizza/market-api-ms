package com.apimicroservices.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.apimicroservices.web.model.loginForm;



@Controller
public class loginController {
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
    public String loginProcess(loginForm loginForm, Model model) {
        if(loginForm.getUsername().equals("admin") && loginForm.getPassword().equals("admin")) {
            model.addAttribute("username", loginForm.getUsername());
            return "index";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }
}
