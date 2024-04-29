package com.uduran.apiserverlet.webapp10.service;

import com.uduran.apiserverlet.webapp10.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ItemService<Producto> {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "tecnologia", 150.0),
                new Producto(2L, "mesa ordenador", "muebles", 200.0),
                new Producto(3L, "Teclado mecanico", "tecnologia", 100.0));
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        ItemService<Producto> service = new ProductoServiceImpl();
        return service.listar().stream().filter(p -> {
            if (nombre.isBlank() || nombre==null){
                return false;
            }
            return p.getNombre().contains(nombre);
        }).findFirst();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }


}