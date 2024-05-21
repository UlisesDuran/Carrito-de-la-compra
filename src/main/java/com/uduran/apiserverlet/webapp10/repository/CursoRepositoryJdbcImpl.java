package com.uduran.apiserverlet.webapp10.repository;

import com.uduran.apiserverlet.webapp10.models.Categoria;
import com.uduran.apiserverlet.webapp10.models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoRepositoryJdbcImpl implements Repository<Curso>{

    private Connection conn;

    public CursoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Curso> listar() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT cursos.*, categorias.categoria_nombre AS nombre_categoria "
                + "FROM cursos JOIN categorias ON cursos.categoria_id = categorias.categoria_id ORDER BY cursos.id ASC;")){
            while (rs.next()){
                Curso c = getCurso(rs);
                cursos.add(c);
            }
        }
        return cursos;
    }

    @Override
    public Optional<Curso> porId(Long id) throws SQLException {
        Optional<Curso> curso = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT cursos.*, categorias.categoria_nombre AS nombre_categoria "
                + "FROM cursos JOIN categorias ON cursos.categoria_id = categorias.categoria_id WHERE cursos.id = ?;")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    curso = Optional.of(getCurso(rs));
                }
            }
        }
        return curso;
    }

    public Optional<Curso> buscar(String nombre) throws SQLException{
        Optional<Curso> curso = Optional.empty();
        try(PreparedStatement stmt = conn.prepareStatement("SELECT cursos.*, categorias.categoria_nombre AS nombre_categoria "
                + "FROM cursos JOIN categorias ON cursos.categoria_id = categorias.categoria_id WHERE cursos.nombre like?;")){
            stmt.setString(2, "%" + nombre + "%");
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    curso = Optional.of(getCurso(rs));
                }
            }
        }
        return curso;
    }

    @Override
    public void guardar(Curso curso) throws SQLException {
        String sql;
        if (curso.getId() != null && curso.getId() > 0){
            sql = "UPDATE cursos SET nombre=?, descripcion=?, instructor=?, duracion=?, precio=?, sku=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO cursos (nombre, descripcion, instructor, duracion, precio, sku, categoria_id, fecha_registro) VALUES (?,?,?,?,?,?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getInstructor());
            stmt.setDouble(4, curso.getDuracion());
            stmt.setDouble(5, curso.getPrecio());
            stmt.setString(6, curso.getSku());
            stmt.setLong(7, curso.getCategoria().getId());
            if (curso.getId() != null && curso.getId() > 0){
                stmt.setLong(8, curso.getId());
            }else {
                stmt.setString(8, curso.getFechaRegistro());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM cursos WHERE id=?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Curso getCurso(ResultSet rs) throws SQLException {
        Curso c = new Curso();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setInstructor(rs.getString("instructor"));
        c.setDuracion(rs.getDouble("duracion"));
        c.setPrecio(rs.getDouble("precio"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre((rs.getString("nombre_categoria")));
        c.setCategoria(categoria);
        return c;
    }
}
