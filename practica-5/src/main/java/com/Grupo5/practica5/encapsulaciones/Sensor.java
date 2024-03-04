package com.Grupo5.practica5.encapsulaciones;

public class Sensor {
    private int idDispositivo;

   public Sensor(int idDispositivo) {
       this.idDispositivo = idDispositivo;
   }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

}
