package com.reuse.reuse.RepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reuse.reuse.Entity.Cliente;
import com.reuse.reuse.Repository.ClienteRepository;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    // Inyección de dependencia de JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public ClienteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de filas para la entidad cliente
    private RowMapper<Cliente> clienteRowMapper = new RowMapper<>() {
        @Override
        public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cliente c = new Cliente();
            c.setId(rs.getLong("id"));
            c.setNombre(rs.getString("nombre"));
            c.setDocumento(rs.getString("documento"));
            c.setTelefono(rs.getString("telefono"));
            c.setActivo(rs.getBoolean("activo"));
            return c;
        }
    };

    // Listar todos los clientes
    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, clienteRowMapper);
    }

    // Buscar cliente por ID
    @Override
    public Cliente findById(Long id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, clienteRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    // Guardar nuevo cliente
    @Override
    public void save(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, documento, telefono, activo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql, 
            cliente.getNombre(), 
            cliente.getDocumento(), 
            cliente.getTelefono(), 
            cliente.isActivo()
        );
    }

    // Actualizar cliente existente
    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ?, documento = ?, telefono = ?, activo = ? WHERE id = ?";
        jdbcTemplate.update(
            sql, 
            cliente.getNombre(), 
            cliente.getDocumento(), 
            cliente.getTelefono(), 
            cliente.isActivo(), 
            cliente.getId()
        );
    }

    // Activar o desactivar cliente
    @Override
    public void activarDesactivar(Long id, boolean activo) {
        String sql = "UPDATE cliente SET activo = ? WHERE id = ?";
        jdbcTemplate.update(sql, activo, id);
    }

}
