package com.grupo5.microservicionotificacion.reporte.controladores;

import com.grupo5.microservicionotificacion.reporte.colecciones.Factura;
import com.grupo5.microservicionotificacion.reporte.servicios.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @RequestMapping("/listar")
    public List<Factura> listar() {
        return facturaService.listar();
    }

    @RequestMapping("/listar-usuario/{id}")
    public List<Factura> listarByusuario(@PathVariable("id") String id) {
        return facturaService.listarByUsuario(id);
    }

    @RequestMapping("/buscar/{id}")
    public Factura buscar(@PathVariable("id") String id) {
        return facturaService.buscarById(id);
    }

    @PostMapping("/generar/{idusuario}")
    public void generar(@PathVariable("idusuario") String idusuario,@RequestParam("total") float total) {
        facturaService.generar(idusuario,total);
    }
}
