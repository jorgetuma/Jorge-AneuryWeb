package com.Grupo5.practica5.encapsulaciones;

import java.util.Date;

public class TramaJSON {
    private int idDispositivo;
    private Number temperatura;
    private Number humedad;
    private Date fechaGeneracion;

    public TramaJSON(int idDispositivo, Number temperatura, Number humedad, Date fechaGeneracion) {
        this.idDispositivo = idDispositivo;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Number getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Number temperatura) {
        this.temperatura = temperatura;
    }

    public Number getHumedad() {
        return humedad;
    }

    public void setHumedad(Number humedad) {
        this.humedad = humedad;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
}
