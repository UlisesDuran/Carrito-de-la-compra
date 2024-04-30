package com.uduran.apiserverlet.webapp10.repository;

import com.uduran.apiserverlet.webapp10.models.Categoria;
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

        try(Statement stmt= conn.createStatement(); ResultSet rs= stmt.executeQuery("SELECT productos.*, categorias.categoria_nombre AS nombre_categoria" +
                " FROM productos JOIN categorias ON productos.categoria_id = categorias.categoria_id;")){

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
        try(PreparedStatement stmt= conn.prepareStatement("SELECT productos.*, categorias.categoria_nombre AS nombre_categoria "
                + "FROM productos JOIN categorias ON productos.categoria_id = categorias.categoria_id WHERE productos.id = ?;")) {
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
        String sql;
        if (producto.getId() != null && producto.getId() > 0){
            sql = "UPDATE productos SET nombre=?, precio=?, sku=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, sku, categoria_id, fecha_registro) VALUES (?,?,?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0){
                stmt.setLong(5, producto.getId());
            }else {
                stmt.setString(5, producto.getFechaRegistro());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("nombre_categoria"));
        p.setCategoria(categoria);
        p.setPrecio(rs.getDouble("precio"));
        return p;
    }
}
