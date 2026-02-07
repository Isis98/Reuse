package com.reuse.reuse.Repository;

import java.util.List;

import com.reuse.reuse.Entity.Pedido;

public interface PedidoRepository {

    List<Pedido> findAll();

    Pedido findById(Long id);

    void save(Pedido pedido);

    void update(Pedido pedido);

    void cancelar(Long id);

}
