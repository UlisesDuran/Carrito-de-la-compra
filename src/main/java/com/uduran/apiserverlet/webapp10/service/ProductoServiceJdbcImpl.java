package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.exceptions.ServiceJdbcException;
import com.uduran.apiserverlet.webapp10.models.Producto;
import com.uduran.apiserverlet.webapp10.repository.ProductoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{

    private ProductoRepositoryJdbcImpl repositoryJdbc;

    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> buscarProducto(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return repositoryJdbc.porId(id);
            // of nullable convierte lo que devuelva porId que para el caso es un producto
            // a un optional.
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
