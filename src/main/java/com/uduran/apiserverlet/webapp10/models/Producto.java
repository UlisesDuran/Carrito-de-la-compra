package com.uduran.apiserverlet.webapp10.models;

public class Producto {
    private Long id;
    private String nombre;
    private String tipo;
    private Double precio;

    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}