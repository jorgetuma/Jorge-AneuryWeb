package com.grupo5.microserviciocarritocompras.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CarritoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCarrito;
    private int idUsuario;

    @ElementCollection
    @CollectionTable(name = "id-libros")
    @Column(name = "idlibro")
    private List<String> libros;

    public CarritoCompra() {

    }

    public CarritoCompra(int idUsuario) {
        this.idUsuario = idUsuario;
        this.libros = new ArrayList<>();
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<String> getLibros() {
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }
}
