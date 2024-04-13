package com.uduran.apiserverlet.webapp10.repository;

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
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM cursos")){
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
        try (PreparedStatement stmt = conn.prepareStatement("SELECT c.* FROM cursos WHERE c.id=?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    curso = Optional.of(getCurso(rs));
                }
            }
        }
        return curso;
    }

    public Optional<Curso> buscarCurso(String nombre) throws SQLException{
        Optional<Curso> curso = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT c.* FROM cursos where c.nombre like=?")){
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
    }

    @Override
    public void eliminar(Long id) throws SQLException {
    }

    private static Curso getCurso(ResultSet rs) throws SQLException {
        Curso c = new Curso();
        c.setCurso(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setInstructor(rs.getString("instructor"));
        c.setDuracion(rs.getDouble("duracion"));
        return c;
    }
}
