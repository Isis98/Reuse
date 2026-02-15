package com.reuse.reuse.Service;

import java.util.List;

import com.reuse.reuse.Entity.DetallePedido;

public interface DetallePedidoService {

    void agregarDetalle(DetallePedido detalle);

    List<DetallePedido> listarPorPedido(Long pedidoId);

    void eliminarPorPedido(Long pedidoId);
    
}
