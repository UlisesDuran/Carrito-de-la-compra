package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.exceptions.ServiceJdbcException;
import com.uduran.apiserverlet.webapp10.models.Categoria;
import com.uduran.apiserverlet.webapp10.models.Curso;
import com.uduran.apiserverlet.webapp10.models.Producto;
import com.uduran.apiserverlet.webapp10.repository.CategoriaRepositoryImpl;
import com.uduran.apiserverlet.webapp10.repository.CursoRepositoryJdbcImpl;
import com.uduran.apiserverlet.webapp10.repository.ProductoRepositoryJdbcImpl;
import com.uduran.apiserverlet.webapp10.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImpl implements ItemService<Categoria>{
    private Repository<Curso> repositoryCursoJdbc;
    private Repository<Producto> repositoryProductoJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public CategoriaServiceJdbcImpl(Connection conn) {
        this.repositoryCursoJdbc = new CursoRepositoryJdbcImpl(conn);
        this.repositoryProductoJdbc = new ProductoRepositoryJdbcImpl(conn);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(conn);
    }

    @Override
    public List<Categoria> listar() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> buscar(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Categoria> porId(Long id) {
        try {
            return repositoryCategoriaJdbc.porId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Categoria categoria) {
        try {
            repositoryCategoriaJdbc.guardar(categoria);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryCategoriaJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
