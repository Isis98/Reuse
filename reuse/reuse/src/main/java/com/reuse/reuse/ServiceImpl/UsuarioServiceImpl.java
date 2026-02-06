package com.reuse.reuse.ServiceImpl;

import com.reuse.reuse.Entity.Usuario;
import com.reuse.reuse.Repository.UsuarioRepository;
import com.reuse.reuse.Service.UsuarioService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    // Inyección del repositorio de Usuario
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Busca un usuario por su nombre de usuario
    @Override
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Lista todos los usuarios
    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // Guarda un nuevo usuario
    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

}
