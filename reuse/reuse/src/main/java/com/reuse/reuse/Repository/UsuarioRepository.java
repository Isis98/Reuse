package com.reuse.reuse.Repository;

import java.util.List;

import com.reuse.reuse.Entity.Usuario;

public interface UsuarioRepository {

    // Método para encontrar un usuario por su nombre de usuario
    Usuario findByUsername(String username);

    // Método para obtener todos los usuarios
    List<Usuario> findAll();

    // Método para guardar un usuario en el repositorio
    void save(Usuario usuario);

}
