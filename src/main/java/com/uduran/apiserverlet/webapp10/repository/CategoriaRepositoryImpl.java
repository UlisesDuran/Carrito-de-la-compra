package com.uduran.apiserverlet.webapp10.repository;

import com.uduran.apiserverlet.webapp10.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRepositoryImpl implements Repository<Categoria>{
    private Connection conn;

    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias;")){
            while(rs.next()){
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }



    @Override
    public Optional<Categoria> porId(Long id) throws SQLException {
        Optional<Categoria> categoria = Optional.empty();
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias AS c WHERE c.id=?;")){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    categoria = Optional.of(getCategoria(rs));
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("categoria_nombre"));
        return categoria;
    }
}
