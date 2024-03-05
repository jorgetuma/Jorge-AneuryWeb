package com.Grupo5.practica5.servicios;

import com.Grupo5.practica5.encapsulaciones.Sensor;
import com.Grupo5.practica5.encapsulaciones.TramaJSON;
import com.Grupo5.practica5.repositorios.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DispositivoService {
    private final List<Sensor> dispositivosActivos = new ArrayList<>();

    private final DispositivoRepository dispositivoRepository;

    private SimpMessagingTemplate template;

    @Autowired
    public DispositivoService(DispositivoRepository dispositivoRepository, SimpMessagingTemplate template) {
        this.dispositivoRepository = dispositivoRepository;
        this.template = template;
    }

    public List<Sensor> getDispositivosActivos() {
        return dispositivosActivos;
    }

    public List<TramaJSON> obtenerTramasByIdDispositivo(int idDispositivo) {
        return dispositivoRepository.findAllByIdDispositivo(idDispositivo);
    }

    public void insertarTrama(TramaJSON tramaJSON) {
        dispositivoRepository.save(tramaJSON);
        template.convertAndSend("/topic/message", tramaJSON);
    }

    public void insertarDispositivo(Sensor sensor) {
        dispositivosActivos.add(sensor);
    }

}
