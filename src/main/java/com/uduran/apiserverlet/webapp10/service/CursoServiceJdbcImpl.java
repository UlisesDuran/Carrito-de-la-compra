package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.exceptions.ServiceJdbcException;
import com.uduran.apiserverlet.webapp10.models.Curso;
import com.uduran.apiserverlet.webapp10.repository.CursoRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CursoServiceJdbcImpl implements ItemService<Curso> {
    private CursoRepositoryJdbcImpl repositoryJdbc;

    public CursoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new CursoRepositoryJdbcImpl(connection);
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
}
