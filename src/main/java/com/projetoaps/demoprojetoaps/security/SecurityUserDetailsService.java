package com.projetoaps.demoprojetoaps.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetoaps.demoprojetoaps.entity.Usuario;
import com.projetoaps.demoprojetoaps.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        if (usuario != null) {
            UserDetails userDetails = new UserDetails(usuario);
            return userDetails;
        }
        return null;
    }
    
}
