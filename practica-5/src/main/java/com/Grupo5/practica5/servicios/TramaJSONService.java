package com.Grupo5.practica5.servicios;

import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TramaJSONService {
    public List<TramaJSON> generarTramasJSON(int id, int n) {
        List<TramaJSON> lista = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i<n;i++) {
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
