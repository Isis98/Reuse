package com.reuse.reuse.RepositoryImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reuse.reuse.Entity.Pedido;
import com.reuse.reuse.Repository.PedidoRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    // Inyección de dependencia de JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public PedidoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapero de filas para la entidad Pedido
    private RowMapper<Pedido> pedidoRowMapper = new RowMapper<>() {
        @Override
        public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pedido p = new Pedido();
            p.setId(rs.getLong("id"));
            p.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            p.setTotal(rs.getDouble("total"));
            p.setActivo(rs.getBoolean("activo"));
            p.setClienteId(rs.getLong("cliente_id"));
            return p;
        }
    };

    // Listar todos los pedidos
    @Override
    public List<Pedido> findAll() {
        return jdbcTemplate.query("SELECT * FROM pedido", pedidoRowMapper);
    }

    // Buscar pedido por ID
    @Override
    public Pedido findById(Long id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, pedidoRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    // Guardar un nuevo pedido
    @Override
    public void save(Pedido pedido) {
        String sql = "INSERT INTO pedido (fecha, total, activo, cliente_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.isActivo(),
                pedido.getClienteId()
        );
    }

    // Actualizar un pedido existente
    @Override
    public void update(Pedido pedido) {
        String sql = "UPDATE pedido SET fecha = ?, total = ?, activo = ?, cliente_id = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.isActivo(),
                pedido.getClienteId(),
                pedido.getId()
        );
    }

    // Cancelar un pedido (marcar como inactivo)
    @Override
    public void cancelar(Long id) {
        String sql = "UPDATE pedido SET activo = false WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
