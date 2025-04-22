package com.amaris.apiFondos.repository;

import com.amaris.apiFondos.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private final Usuario usuario = new Usuario("1", "Cliente", 500000);

    public Usuario getUsuario() {
        return usuario;
    }
}
