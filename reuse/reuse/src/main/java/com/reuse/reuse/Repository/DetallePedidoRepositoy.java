package com.reuse.reuse.Repository;

import java.util.List;

import com.reuse.reuse.Entity.DetallePedido;

public interface DetallePedidoRepositoy {

    void save(DetallePedido detalle);

    List<DetallePedido> findByPedidoId(Long pedidoId);

    void deleteByPedidoId(Long pedidoId);
    
}
