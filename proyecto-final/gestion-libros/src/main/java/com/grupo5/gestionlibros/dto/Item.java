package com.grupo5.gestionlibros.dto;

public class Item {
    private Libro libro;
    private int cantidad;

    public Item(Libro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float calcularTotal() {
        return cantidad * libro.getPrecio();
    }
}
