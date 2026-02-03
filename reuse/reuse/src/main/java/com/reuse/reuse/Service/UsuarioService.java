package com.reuse.reuse.Service;

import java.util.List;

import com.reuse.reuse.Entity.Usuario;

public interface UsuarioService {

    Usuario buscarPorUsername(String username);

    List<Usuario> listar();

    void guardar(Usuario usuario);
    
}
