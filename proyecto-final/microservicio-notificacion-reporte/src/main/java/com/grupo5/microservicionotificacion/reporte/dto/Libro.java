package com.grupo5.microservicionotificacion.reporte.dto;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private String genero;
    private String editorial;
    private float precio;

    public  Libro() {

    }

    public Libro(String titulo, String autor, String genero, String editorial,float precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editorial = editorial;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setIsbn(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
