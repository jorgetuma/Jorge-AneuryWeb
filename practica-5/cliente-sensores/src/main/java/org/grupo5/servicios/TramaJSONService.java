package org.grupo5.servicios;

import org.grupo5.encapsulaciones.TramaJSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TramaJSONService {
    public List<TramaJSON> generarTramasJSON(int n) {
        List<TramaJSON> lista = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i<n;i++) {
            int id = rand.nextInt(2) + 1;
            Number humedad = rand.nextFloat(100);
            Number temperatura = rand.nextInt(100);
            Date fecha = new Date(System.currentTimeMillis() + rand.nextInt(100));
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = formatoFecha.format(fecha);
            TramaJSON trama = new TramaJSON(id,temperatura,humedad,fechaFormateada);
            lista.add(trama);
        }
        return lista;
    }

    public TramaJSON generarTramaJson(int id) {
        Random rand = new Random();
        Number humedad = rand.nextFloat(100);
        Number temperatura = rand.nextInt(100);
        Date fecha = new Date(System.currentTimeMillis() + rand.nextInt(100));
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formatoFecha.format(fecha);
        TramaJSON trama = new TramaJSON(id,temperatura,humedad,fechaFormateada);

        return trama;
    }
}
