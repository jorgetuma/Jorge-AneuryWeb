package com.grupo5.microserviciocarritocompras.servicios;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import com.grupo5.microserviciocarritocompras.entidades.Pedido;
import com.grupo5.microserviciocarritocompras.repositorios.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        return pedidoRepository.findPedidoByIdPedido(id);
    }

    public void insertar(String user, CarritoCompra carritoCompra, Map<String,String> params) {
        Pedido pedido = new Pedido();
        List<String> libros = carritoCompra.getLibros();
        pedido.setLibros(libros);
        pedido.setIdUser(user);
        pedido.setFactura(params.get("invoice"));
        pedido.setTransaccion(params.get("txn_id"));
        pedido.setNombre(params.get("item_name"));
        pedido.setEstatusPago(params.get("payment_status"));

        pedido.setMontoCompra(new BigDecimal(params.get("payment_gross")));
        pedido.setMontoManejo(new BigDecimal(params.get("handling_amount")));
        pedido.setMontoFee(new BigDecimal(params.get("payment_fee")));
        pedido.setMontoEnvio(new BigDecimal(params.get("shipping")));

        pedido.setCompradorId(params.get("txn_id"));
        pedido.setEmailComprador(params.get("payer_email"));
        pedido.setFecha(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
        pedido.setVendedor(params.get("business"));

        pedido.setCiudad(params.get("address_city"));
        pedido.setZip(params.get("address_zip"));
        pedido.setEstado(params.get("address_state"));
        pedido.setDireccion(params.get("address_name"));
        pedidoRepository.save(pedido);
    }

    @Transactional
    public void marcarRealizado(String id) {
        Pedido pedido = pedidoRepository.findPedidoByIdPedido(id);
        pedido.setPendiente(false);
    }
}
