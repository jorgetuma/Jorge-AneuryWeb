package org.grupo5.encapsulaciones;

import org.grupo5.servicios.TramaJSONService;

public class Cliente {
    private Publicador publicador;

    public Cliente() {
        this.publicador = new Publicador();
    }


    public void generarDatos(int delay) throws Exception {
        TramaJSONService tramaJSONService = new TramaJSONService();
       TramaJSON trama1,trama2;
       while(true) {
       trama1 = tramaJSONService.generarTramaJson(1);
       trama2 = tramaJSONService.generarTramaJson(2);
       publicador.enviarMensaje(trama1);
       publicador.enviarMensaje(trama2);
       Thread.sleep(delay);
       }
    }

}
