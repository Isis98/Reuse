package com.reuse.reuse.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reuse.reuse.Entity.Pedido;
import com.reuse.reuse.Repository.PedidoRepository;
import com.reuse.reuse.Service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    // Inyección del repositorio de Pedido
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Listar todos los pedidos
    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido por ID
    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Guardar nuevo pedido
    @Override
    public void guardar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    // Cancelar pedido por ID
    @Override
    public void cancelar(Long id) {
        pedidoRepository.cancelar(id);
    }

}
