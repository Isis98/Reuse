package com.reuse.reuse.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reuse.reuse.Entity.Cliente;
import com.reuse.reuse.Repository.ClienteRepository;
import com.reuse.reuse.Service.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService {

    // Inyección del repositorio de Cliente
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos los clientes
    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID
    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    // Guardar nuevo cliente
    @Override
    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Actualizar cliente existente
    @Override
    public void actualizar(Cliente cliente) {
        clienteRepository.update(cliente);
    }

    // Activar o desactivar cliente
    @Override
    public void cambiarEstado(Long id, boolean activo) {
        clienteRepository.activarDesactivar(id, activo);
    }

}
