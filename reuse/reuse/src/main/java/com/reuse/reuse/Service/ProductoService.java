package com.reuse.reuse.Service;

import java.util.List;
import com.reuse.reuse.Entity.Producto;

public interface ProductoService {

    // Lista todos los productos
    List<Producto> listar();

    // Busca un producto por su ID
    Producto buscarPorId(Long id);

    // Guarda un nuevo producto
    void guardar(Producto producto);

    // Actualiza un producto existente
    void actualizar(Producto producto);

    // Activa o desactiva un producto
    void cambiarEstado(Long id, boolean activo);
    
}
