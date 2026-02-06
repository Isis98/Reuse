package com.reuse.reuse.Service;

import java.util.List;

import com.reuse.reuse.Entity.Usuario;

public interface UsuarioService {

    // Busca un usuario por su nombre de usuario
    Usuario buscarPorUsername(String username);

    // Lista todos los usuarios
    List<Usuario> listar();

    // Guarda un nuevo usuario
    void guardar(Usuario usuario);
    
}
