package com.uduran.apiserverlet.webapp10.models;

public class Curso {
    private Long id;
    private String curso;
    private String descripcion;
    private String instructor;
    private double duracion;

    public Curso() {
    }

    public Curso(Long id, String curso, String descripcion, String instructor, double duracion) {
        this.id = id;
        this.curso = curso;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.duracion = duracion;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public String getCurso() {
        return curso;
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
}
