package com.reuse.reuse.Repository;

import java.util.List;

import com.reuse.reuse.Entity.Cliente;

public interface ClienteRepository {

    // Listar todos los clientes
    List<Cliente> findAll();

    // Buscar cliente por ID
    Cliente findById(Long id);

    // Guardar nuevo cliente
    void save(Cliente cliente);

    // Actualizar cliente existente
    void update(Cliente cliente);

    // Activar o desactivar cliente
    void activarDesactivar(Long id, boolean activo);

}
