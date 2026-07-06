package com.apimicroservices.web.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.apimicroservices.web.clients.userClient;
import com.apimicroservices.web.model.LoginRequestDTO;
import com.apimicroservices.web.model.loginForm;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class loginService {
    private final userClient userClient;

    public String login(loginForm form, Model model, HttpSession session) {
        try {
            LoginRequestDTO requestDto = new LoginRequestDTO(
                form.getUsername(), 
                form.getPassword(), 
                form.isRememberMe()
            );

            String token = userClient.login(requestDto);
            session.setAttribute("token", token);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            model.addAttribute("loginForm", form);
            return "login";
        }
    }
}