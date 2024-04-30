package com.uduran.apiserverlet.webapp10.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Curso {
    private Long id;
    private String nombre;
    private String descripcion;
    private String instructor;
    private double duracion;
    private double precio;
    private Categoria categoria;
    private String sku;
    private String fechaRegistro;

    public Curso() {
    }

    public Curso(Long id, String nombre, String descripcion, String instructor, double duracion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.duracion = duracion;
        this.precio = precio;
    }

    public void setId(Long id){this.id = id;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getInstructor() {
        return instructor;
    }

    public double getDuracion() {
        return duracion;
    }

    public double getPrecio() {return precio;}

    public Categoria getCategoria() {
        return categoria;
    }

    public String getSku() {
        return sku;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
}
