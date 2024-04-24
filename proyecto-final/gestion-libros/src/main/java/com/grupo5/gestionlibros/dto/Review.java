package com.grupo5.gestionlibros.dto;

public class Review {
    private String idReview;
    private String idUsuario;
    private String idLibro;
    private String review;
    private int calificacion;

    public Review() {

    }

    public Review(String idUsuario, String review, String idLibro, int calificacion) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.review = review;
        this.calificacion = calificacion;
    }

    public String getIdReview() {
        return idReview;
    }

    public void setIdReview(String idReview) {
        this.idReview = idReview;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
