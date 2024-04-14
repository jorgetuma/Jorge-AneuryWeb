package com.grupo5.microserviciocarritocompras.servicios;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import com.grupo5.microserviciocarritocompras.entidades.Pedido;
import com.grupo5.microserviciocarritocompras.repositorios.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarByEstado(boolean estado) {
        return pedidoRepository.findAllByPendiente(estado);
    }

    public Pedido buscar(String id) {
        return pedidoRepository.findPedidoByIdFactura(id);
    }

    public void insertar(String user, String idFactura, CarritoCompra carritoCompra) {
        Pedido pedido = new Pedido(user,idFactura,true,new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
        List<String> libros = carritoCompra.getLibros();
        pedido.setLibros(libros);
        pedidoRepository.save(pedido);
    }

    @Transactional
    public void marcarRealizado(String id) {
        Pedido pedido = pedidoRepository.findPedidoByIdPedido(id);
        pedido.setPendiente(false);
    }

    public Pedido buscarByFactura(String id) {
        return pedidoRepository.findPedidoByIdFactura(id);
    }
}
