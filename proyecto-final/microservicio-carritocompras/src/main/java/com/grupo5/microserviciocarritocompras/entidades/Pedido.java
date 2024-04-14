package com.grupo5.microserviciocarritocompras.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPedido;
    private String idUser;
    @ElementCollection
    @CollectionTable(name = "id-libro")
    @Column(name = "idlibro")
    private List<String> libros;
    private String idFactura;
    private boolean pendiente;
    private String fecha;

    public Pedido() {

    }

    public Pedido(String idUser, String idFactura, boolean pendiente, String fecha) {
        this.idUser = idUser;
        this.libros = new ArrayList<>();
        this.idFactura = idFactura;
        this.pendiente = pendiente;
        this.fecha = fecha;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getUser() {
        return idUser;
    }

    public void setUser(String idUser) {
        this.idUser = idUser;
    }

    public List<String> getLibros() {
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
