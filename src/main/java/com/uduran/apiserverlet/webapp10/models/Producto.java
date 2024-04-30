package com.uduran.apiserverlet.webapp10.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private Double precio;
    private String sku;
    private String fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre, String categoria, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = new Categoria();
        this.categoria.setNombre(categoria);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fechaRegistro = formatter.format(fechaRegistro);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}