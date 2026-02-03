package com.reuse.reuse.RepositoryImpl;

import com.reuse.reuse.Entity.Usuario;
import com.reuse.reuse.Repository.UsuarioRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Usuario> usuarioRowMapper = new RowMapper<>() {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario u = new Usuario();
            u.setId(rs.getLong("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setActivo(rs.getBoolean("activo"));
            u.setRolId(rs.getLong("rol_id"));
            return u;
        }
    };

    @Override
    public Usuario findByUsername(String username) {
        String sql = "SELECT * FROM usuario WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, usuarioRowMapper, username);
    }

    @Override
    public List<Usuario> findAll() {
        return jdbcTemplate.query("SELECT * FROM usuario", usuarioRowMapper);
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario (username, password, activo, rol_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isActivo(),
                usuario.getRolId()
        );
    }
    
}
