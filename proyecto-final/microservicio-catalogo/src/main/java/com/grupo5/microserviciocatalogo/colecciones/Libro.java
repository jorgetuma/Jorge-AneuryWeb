package com.grupo5.microserviciocatalogo.colecciones;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Libro {
    @Id
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private String editorial;

    public  Libro() {

    }

    public Libro(String titulo, String autor, String genero, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
}
