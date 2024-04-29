package com.uduran.apiserverlet.webapp10.models;

import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private Double precio;
    private String sku;
    private LocalDate fechaRegistro;

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

    public void setCategoria(String nombre) {
        this.categoria = new Categoria();
        this.categoria.setNombre(nombre);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", categoría'" + categoria.getNombre() + '\'' +
                ", precio=" + precio +
                '}';
    }
}