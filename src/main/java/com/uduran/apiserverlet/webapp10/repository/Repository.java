package com.uduran.apiserverlet.webapp10.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> listar() throws SQLException;
    Optional<T> porId(Long id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
