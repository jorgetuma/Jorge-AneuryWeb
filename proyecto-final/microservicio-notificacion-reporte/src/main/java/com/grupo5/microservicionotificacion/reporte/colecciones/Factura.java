package com.grupo5.microservicionotificacion.reporte.colecciones;

import com.grupo5.microserviciocatalogo.colecciones.Libro;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Factura {
    @Id
    private String id;
    private String idUsuario;
    private List<Libro> libros;
    private float total;
    private String fecha;

    public Factura(String idUsuario,float total,String fecha) {
        this.idUsuario = idUsuario;
        this.libros = new ArrayList<>();
        this.total = total;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
