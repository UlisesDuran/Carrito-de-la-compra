package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.exceptions.ServiceJdbcException;
import com.uduran.apiserverlet.webapp10.models.Categoria;
import com.uduran.apiserverlet.webapp10.models.Curso;
import com.uduran.apiserverlet.webapp10.repository.CategoriaRepositoryImpl;
import com.uduran.apiserverlet.webapp10.repository.CursoRepositoryJdbcImpl;
import com.uduran.apiserverlet.webapp10.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CursoServiceJdbcImpl implements ItemService<Curso> {
    private Repository<Curso> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public CursoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new CursoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImpl(connection);
    }

    @Override
    public List<Curso> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Curso> buscar(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Curso> porId(Long id) {
        try {
            return repositoryJdbc.porId(id);
            // of nullable convierte lo que devuelva porId que para el caso es un producto
            // a un optional.
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Curso curso) {
        try {
            repositoryJdbc.guardar(curso);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
