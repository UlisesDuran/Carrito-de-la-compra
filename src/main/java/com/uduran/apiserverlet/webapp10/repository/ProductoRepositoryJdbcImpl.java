package com.uduran.apiserverlet.webapp10.repository;

import com.uduran.apiserverlet.webapp10.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepositoryJdbcImpl implements Repository<Producto>{

    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try(Statement stmt= conn.createStatement(); ResultSet rs= stmt.executeQuery("SELECT productos.*, categorias.categoria AS nombre_categoria" +
                " FROM productos JOIN categorias ON productos.categoriaId = categorias.id;")){
            while (rs.next()){
                Producto p = getProducto(rs);
                productos.add(p);
            }

        }
        return productos;
    }



    @Override
    public Optional<Producto> porId(Long id) throws SQLException {

        Optional<Producto> producto = null;
        try(PreparedStatement stmt= conn.prepareStatement("SELECT productos.*, categorias.categoria AS nombre_categoria" +
                " FROM productos JOIN categorias ON productos.categoriaId = categorias.id WHERE productos.id = ?;")) {
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    producto = Optional.of(getProducto(rs));
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCategoria(rs.getString("nombre_categoria"));
        p.setPrecio(rs.getDouble("precio"));
        return p;
    }
}
