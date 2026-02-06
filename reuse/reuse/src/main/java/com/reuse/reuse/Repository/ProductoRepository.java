package com.reuse.reuse.Repository;

import java.util.List;
import com.reuse.reuse.Entity.Producto;

public interface ProductoRepository {

    // Lista todos los productos
    List<Producto> findAll();

    // Encuentra un producto por su ID
    Producto findById(Long id);

    // Guarda un nuevo producto
    void save(Producto producto);

    // Actualiza un producto existente
    void update(Producto producto);

    // Activa o desactiva un producto
    void activarDesactivar(Long id, boolean activo);
    
}
