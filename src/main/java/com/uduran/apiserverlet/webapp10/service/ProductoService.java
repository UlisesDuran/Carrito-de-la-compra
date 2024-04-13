package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> buscarProducto(String nombre);
    Optional<Producto> porId(Long id);
}