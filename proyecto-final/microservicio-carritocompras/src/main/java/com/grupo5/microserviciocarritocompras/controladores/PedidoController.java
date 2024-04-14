package com.grupo5.microserviciocarritocompras.controladores;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import com.grupo5.microserviciocarritocompras.entidades.Pedido;
import com.grupo5.microserviciocarritocompras.servicios.CarritoCompraService;
import com.grupo5.microserviciocarritocompras.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/crear/{iduser}&{idfactura}")
    public void crearPedido(@PathVariable("iduser") String iduser,@PathVariable("idfactura") String idfactura) {
        CarritoCompra carritoCompra = carritoCompraService.buscarCarritoByUsuario(iduser);
        pedidoService.insertar(iduser,idfactura,carritoCompra);
    }

    @RequestMapping("/buscar/{id}")
    public Pedido buscar(@PathVariable("id") String id) {
        return pedidoService.buscar(id);
    }

    @RequestMapping("/buscar-factura/{id}")
    public Pedido buscarByFactura(@PathVariable("id") String id) {
        return  pedidoService.buscarByFactura(id);
    }

    @RequestMapping("/pedido-realizado/{id}")
    public void pedidoRealizado(@PathVariable("id") String id) {
        pedidoService.marcarRealizado(id);
    }

}
