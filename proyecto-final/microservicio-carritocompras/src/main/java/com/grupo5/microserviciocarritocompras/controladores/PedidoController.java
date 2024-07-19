package com.grupo5.microserviciocarritocompras.controladores;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import com.grupo5.microserviciocarritocompras.entidades.Pedido;
import com.grupo5.microserviciocarritocompras.servicios.CarritoCompraService;
import com.grupo5.microserviciocarritocompras.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final CarritoCompraService carritoCompraService;

    @Autowired
    public PedidoController(PedidoService pedidoService,CarritoCompraService carritoCompraService) {
        this.pedidoService = pedidoService;
        this.carritoCompraService = carritoCompraService;
    }

    @RequestMapping("/listar")
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @RequestMapping("/listar-pendiente")
    public List<Pedido> listarPendientes() {
        return pedidoService.listarByEstado(true);
    }

    @RequestMapping("/listar-realizadas")
    public List<Pedido> listarRealizadas() {
        return pedidoService.listarByEstado(false);
    }

    @RequestMapping("/listar-usuario/{id}")
    public List<Pedido> listarByUsuario(@PathVariable("id") int id) {return pedidoService.listarByUsuario(id);}

    @RequestMapping("/listar-fechactual")
    public List<Pedido> listarByFecha() {return pedidoService.listarByFecha(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));}

    @PostMapping("/procesar/{iduser}")
    public void procesarPedido(@PathVariable("iduser") int iduser, @RequestBody Map<String,String> params) {
        CarritoCompra carritoCompra = carritoCompraService.buscarCarritoByUsuario(iduser);
        pedidoService.insertar(iduser,carritoCompra,params);
    }

    @RequestMapping("/buscar/{id}")
    public Pedido buscar(@PathVariable("id") String id) {
        return pedidoService.buscar(id);
    }

    @RequestMapping("/pedido-realizado/{id}")
    public void pedidoRealizado(@PathVariable("id") String id) {
        pedidoService.marcarRealizado(id);
    }

}
