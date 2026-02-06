package com.reuse.reuse.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reuse.reuse.Entity.Producto;
import com.reuse.reuse.Repository.ProductoRepository;
import com.reuse.reuse.Service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    // Inyección del repositorio de Producto
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listar todos los productos
    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // Buscar producto por ID
    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Guardar nuevo producto
    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    // Actualizar producto existente
    @Override
    public void actualizar(Producto producto) {
        productoRepository.update(producto);
    }

    // Activar o desactivar producto
    @Override
    public void cambiarEstado(Long id, boolean activo) {
        productoRepository.activarDesactivar(id, activo);
    }

}
