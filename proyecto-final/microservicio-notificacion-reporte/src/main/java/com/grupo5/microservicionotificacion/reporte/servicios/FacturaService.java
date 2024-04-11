package com.grupo5.microservicionotificacion.reporte.servicios;

import com.grupo5.microservicionotificacion.reporte.colecciones.Factura;
import com.grupo5.microservicionotificacion.reporte.repositorios.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public List<Factura> listarByUsuario(String id) {
        return facturaRepository.findAllByIdUsuario(id);
    }

    public Factura buscarById(String id) {
        return facturaRepository.findFacturaById(id);
    }

    public void generar(String idUsuario,float total) {
        Factura factura = new Factura(idUsuario,total,new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
        facturaRepository.insert(factura);
    }
}
