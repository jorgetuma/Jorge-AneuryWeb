package com.Grupo5.practica5.servicios;

import com.Grupo5.practica5.encapsulaciones.Sensor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DispositivoService {
    private final List<Sensor> dispositivosActivos;

    public DispositivoService() {
        this.dispositivosActivos = new ArrayList<>();
    }

    public List<Sensor> getDispositivosActivos() {
        return dispositivosActivos;
    }

    public Sensor findDispositivoById(int id) {
        for (Sensor s:dispositivosActivos) {
            if (id == s.getIdDispositivo()) {
                return s;
            }
        }
        return null;
    }
}
