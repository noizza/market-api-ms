package com.apimicroservices.web.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apimicroservices.web.clients.userClient;
import com.apimicroservices.web.model.LoginRequestDTO;
import com.apimicroservices.web.model.loginForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class loginService {
    private final userClient userClient;

    public String login(loginForm form, RedirectAttributes redirectAttributes, Model model) {
        try {
            LoginRequestDTO requestDto = new LoginRequestDTO(
                form.getUsername(), 
                form.getPassword(), 
                false
            );

            String token = userClient.login(requestDto);
            redirectAttributes.addFlashAttribute("username", form.getUsername());
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            model.addAttribute("loginForm", form);
            return "login";
        }
    }
}