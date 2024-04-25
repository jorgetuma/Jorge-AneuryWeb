package com.grupo5.gestionlibros.dto;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompra {
    private String idCarrito;
    private String idUsuario;

    private List<String> libros;

    public CarritoCompra() {
        this.libros = new ArrayList<>();
    }

    public CarritoCompra(String idUsuario) {
        this.idUsuario = idUsuario;
        this.libros = new ArrayList<>();
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<String> getLibros() {
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }
}
