package com.projetoaps.demoprojetoaps.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.projetoaps.demoprojetoaps.entity.Usuario;
import com.projetoaps.demoprojetoaps.service.UsuarioService;

@Component
public class SecurityLoggedUser {

    @Autowired
    private UsuarioService usuarioService;
    
    public Usuario BuscarPorUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.buscarPorUsername(username);
            return usuario;
        }
        return null;
    }

}
