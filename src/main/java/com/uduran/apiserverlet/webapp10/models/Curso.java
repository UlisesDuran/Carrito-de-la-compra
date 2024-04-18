package com.uduran.apiserverlet.webapp10.models;

public class Curso {
    private Long id;
    private String nombre;
    private String descripcion;
    private String instructor;
    private double duracion;
    private double precio;

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

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
