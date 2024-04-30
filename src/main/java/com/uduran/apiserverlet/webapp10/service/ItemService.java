package com.uduran.apiserverlet.webapp10.service;

import java.util.List;
import java.util.Optional;

public interface ItemService<T> {
    List<T> listar();
    Optional<T> buscar(String nombre);
    Optional<T> porId(Long id);
    void guardar(T t);
    void eliminar(Long id);

}