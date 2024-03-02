package com.Grupo5.practica5.encapsulaciones;

import com.Grupo5.practica5.servicios.DispositivoService;
import com.Grupo5.practica5.servicios.TramaJSONService;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private int idDispositivo;
   private List<TramaJSON> datos;

   private Productor productor;


   public Sensor(int idDispositivo) {
       this.idDispositivo = idDispositivo;
       this.productor = new Productor();
       this.datos = new ArrayList<>();
   }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public List<TramaJSON> getDatos() {
        return datos;
    }

    public void setDatos(List<TramaJSON> datos) {
        this.datos = datos;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public void generarDatos(int n) throws JMSException {
       TramaJSONService tramaJSONService = new TramaJSONService();
       datos = tramaJSONService.generarTramasJSON(idDispositivo, n);
       for(int i = 0;i < n; i++) {
           productor.enviarMensaje(datos.get(i));
       }
   }
}
