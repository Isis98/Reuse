package com.reuse.reuse.RepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reuse.reuse.Entity.Producto;
import com.reuse.reuse.Repository.ProductoRepository;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository{

    // Inyección de dependencia de JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    public ProductoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeo de filas para la entidad producto
    private RowMapper<Producto> productoRowMapper = new RowMapper<>() {
        @Override
        public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Producto p = new Producto();
            p.setId(rs.getLong("id"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            p.setActivo(rs.getBoolean("activo"));
            p.setCategoriaId(rs.getLong("categoria_id"));
            return p;
        }
    };
    
    // Listar todos los productos
    @Override
    public List<Producto> findAll() {
        String sql = "SELECT * FROM producto";
        return jdbcTemplate.query(sql, productoRowMapper);
    }

    // Buscar producto por ID
    @Override
    public Producto findById(Long id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, productoRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    // Guardar nuevo producto
    @Override
    public void save(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, activo, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock(), producto.isActivo(), producto.getCategoriaId());
    }

    // Actualizar producto existente
    @Override
    public void update(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, activo = ?, categoria_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock(), producto.isActivo(), producto.getCategoriaId(), producto.getId());
    }

    // Activar o desactivar producto
    @Override
    public void activarDesactivar(Long id, boolean activo) {
        String sql = "UPDATE producto SET activo = ? WHERE id = ?";
        jdbcTemplate.update(sql, activo, id);
    }

}
