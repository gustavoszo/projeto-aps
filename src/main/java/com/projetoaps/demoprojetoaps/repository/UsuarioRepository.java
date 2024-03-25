package com.projetoaps.demoprojetoaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoaps.demoprojetoaps.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
    
}
