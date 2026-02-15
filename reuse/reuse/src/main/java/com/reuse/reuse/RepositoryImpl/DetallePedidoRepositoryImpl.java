package com.reuse.reuse.RepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reuse.reuse.Entity.DetallePedido;
import com.reuse.reuse.Repository.DetallePedidoRepositoy;

@Repository
public class DetallePedidoRepositoryImpl implements DetallePedidoRepositoy{

    private final JdbcTemplate jdbcTemplate;

    public DetallePedidoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de filas para la entidad detalle de pedido
    private RowMapper<DetallePedido> detalleRowMapper = new RowMapper<>() {
        @Override
        public DetallePedido mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetallePedido d = new DetallePedido();
            d.setId(rs.getLong("id"));
            d.setCantidad(rs.getInt("cantidad"));
            d.setPrecio(rs.getDouble("precio"));
            d.setPedidoId(rs.getLong("pedido_id"));
            d.setProductoId(rs.getLong("producto_id"));
            return d;
        }
    };

    // Agregar un nuevo detalle de pedido a la base de datos
    @Override
    public void save(DetallePedido d) {
        String sql = """
            INSERT INTO detalle_pedido (cantidad, precio, pedido_id, producto_id)
            VALUES (?, ?, ?, ?)""";
        jdbcTemplate.update(sql, d.getCantidad(), d.getPrecio(), d.getPedidoId(), d.getProductoId());
    }

    // Encontrar detalles de pedido por el ID del pedido
    @Override
    public List<DetallePedido> findByPedidoId(Long pedidoId) {
        String sql = "SELECT * FROM detalle_pedido WHERE pedido_id = ?";
        return jdbcTemplate.query(sql, detalleRowMapper, pedidoId);
    }

    // Eliminar detalles de pedido por el ID del pedido
    @Override
    public void deleteByPedidoId(Long pedidoId) {
        String sql = "DELETE FROM detalle_pedido WHERE pedido_id = ?";
        jdbcTemplate.update(sql, pedidoId);
    }

}
