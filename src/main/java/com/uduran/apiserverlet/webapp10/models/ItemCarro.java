package com.uduran.apiserverlet.webapp10.models;

import java.util.Objects;
import java.util.Optional;

public class ItemCarro {
    private int cantidad;
    private Producto producto;
    private Curso curso;

    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public ItemCarro(int cantidad, Curso curso){
        this.cantidad = cantidad;
        this.curso = curso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Curso getCurso(){
        return curso;
    }

    public void setCurso(Curso curso){
        this.curso = curso;
    }

    public Double getImporte(){
        Optional<Curso> curso = Optional.ofNullable(this.curso);
        Optional<Producto> producto = Optional.ofNullable(this.producto);
        return curso.map(value -> this.cantidad * value.getPrecio()).orElseGet(() -> this.cantidad * producto.get().getPrecio());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) object;
        Optional<Curso> otherCurso = Optional.ofNullable(itemCarro.getCurso());
        Optional<Producto> otherProducto = Optional.ofNullable(itemCarro.getProducto());
        Optional<Curso> thisCurso = Optional.ofNullable(this.getCurso());
        Optional<Producto> thisProducto = Optional.ofNullable(this.getProducto());

        if (otherCurso.isPresent() && thisCurso.isPresent()){
            return Objects.equals(otherCurso.get().getId(), thisCurso.get().getId())
                    && Objects.equals(otherCurso.get().getNombre(), thisCurso.get().getNombre());
        }else if (otherProducto.isPresent() && thisProducto.isPresent()){
            return Objects.equals(otherProducto.get().getId(), thisProducto.get().getId())
                    && Objects.equals(otherProducto.get().getNombre(), thisProducto.get().getNombre());
        }
        return false;
    }

}


