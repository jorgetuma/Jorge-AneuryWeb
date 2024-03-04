package org.grupo5.encapsulaciones;

import java.io.Serializable;

public class TramaJSON implements Serializable {
    private int idDispositivo;
    private Number temperatura;
    private Number humedad;
    private String fechaGeneracion;

    public TramaJSON(int idDispositivo, Number temperatura, Number humedad, String fechaGeneracion) {
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

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
}