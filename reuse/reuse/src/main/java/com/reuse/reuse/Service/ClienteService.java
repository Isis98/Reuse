package com.reuse.reuse.Service;

import java.util.List;

import com.reuse.reuse.Entity.Cliente;

public interface ClienteService {

    // Listar todos los clientes
    List<Cliente> listar();

    // Buscar cliente por ID
    Cliente buscarPorId(Long id);

    // Guardar nuevo cliente
    void guardar(Cliente cliente);

    // Actualizar cliente existente
    void actualizar(Cliente cliente);

    // Activar o desactivar cliente
    void cambiarEstado(Long id, boolean activo);

}
