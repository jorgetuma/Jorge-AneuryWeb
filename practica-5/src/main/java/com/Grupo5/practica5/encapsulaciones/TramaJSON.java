package com.Grupo5.practica5.encapsulaciones;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class TramaJSON implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idTrama;
    private int idDispositivo;
    private int temperatura;
    private float humedad;
    private String fechaGeneracion;

    public TramaJSON() {

    }

    public String getIdTrama() {
        return idTrama;
    }

    public void setIdTrama(String idTrama) {
        this.idTrama = idTrama;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
}
