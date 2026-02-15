package com.reuse.reuse.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reuse.reuse.Entity.DetallePedido;
import com.reuse.reuse.Repository.DetallePedidoRepositoy;
import com.reuse.reuse.Service.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepositoy repository;

    public DetallePedidoServiceImpl(DetallePedidoRepositoy repository) {
        this.repository = repository;
    }

    // Agregar un nuevo detalle de pedido
    @Override
    public void agregarDetalle(DetallePedido detalle) {
        repository.save(detalle);
    }

    // Listar detalles de pedido por el ID del pedido
    @Override
    public List<DetallePedido> listarPorPedido(Long pedidoId) {
        return repository.findByPedidoId(pedidoId);
    }

    // Eliminar detalles de pedido por el ID del pedido
    @Override
    public void eliminarPorPedido(Long pedidoId) {
        repository.deleteByPedidoId(pedidoId);
    }

}
