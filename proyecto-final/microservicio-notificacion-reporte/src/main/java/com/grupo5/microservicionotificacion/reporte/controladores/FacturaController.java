package com.grupo5.microservicionotificacion.reporte.controladores;

import com.grupo5.microservicionotificacion.reporte.colecciones.Factura;
import com.grupo5.microservicionotificacion.reporte.servicios.FacturaService;
import com.grupo5.microservicionotificacion.reporte.servicios.NotificacionService;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    private final FacturaService facturaService;
    private final NotificacionService notificacionService;

    @Autowired
    public FacturaController(FacturaService facturaService,NotificacionService notificacionService) {
        this.facturaService = facturaService;
        this.notificacionService = notificacionService;
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

    @PostMapping("/generar/{idusuario}&{correo}")
    public void generar(@PathVariable("idusuario") String idusuario,@PathVariable("correo") String correo,@RequestParam("total") float total) {
       Factura factura = facturaService.generar(idusuario,total);
        JasperPrint print = facturaService.generarReporte(factura);
        String mensaje = "Se ha generado su factura " + factura.getId() + "con un monto de " + factura.getTotal();
        notificacionService.enviarFactura(correo,mensaje,print);
    }
}
