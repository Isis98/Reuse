package com.reuse.reuse.Service;

import java.util.List;

import com.reuse.reuse.Entity.Pedido;

public interface PedidoService {
    
    List<Pedido> listar();

    Pedido buscarPorId(Long id);

    void guardar(Pedido pedido);

    void cancelar(Long id);

    void calcularTotal(Long pedidoId);

}
