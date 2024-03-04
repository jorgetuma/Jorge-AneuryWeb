package org.grupo5.encapsulaciones;

import org.grupo5.servicios.TramaJSONService;

public class Cliente {
    private Publicador publicador;

    public Cliente() {
        this.publicador = new Publicador();
    }


    public void generarDatos(int delay) throws Exception {
        TramaJSONService tramaJSONService = new TramaJSONService();
       TramaJSON trama;
       while(true) {
       trama = tramaJSONService.generarTramaJson();
       publicador.enviarMensaje(trama);
       Thread.sleep(delay);
       }
    }

}
