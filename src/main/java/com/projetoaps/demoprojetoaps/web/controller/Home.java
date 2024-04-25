package com.projetoaps.demoprojetoaps.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projetoaps.demoprojetoaps.entity.Usuario;
import com.projetoaps.demoprojetoaps.security.SecurityLoggedUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class Home {

    private final SecurityLoggedUser loggedUser;

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "/login";
    }

    @ModelAttribute("user")
    public Usuario usuarioLogado() {
        return loggedUser.BuscarPorUsuarioLogado();
    }
    
}
