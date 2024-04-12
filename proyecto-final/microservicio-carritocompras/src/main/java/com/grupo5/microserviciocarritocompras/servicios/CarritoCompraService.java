package com.grupo5.microserviciocarritocompras.servicios;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import com.grupo5.microserviciocarritocompras.repositorios.CarritoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoCompraService {
    private final CarritoRepository carritoRepository;

    @Autowired
    public CarritoCompraService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public String crearCarrito(String idUsuario) {
        CarritoCompra carritoCompra = new CarritoCompra(idUsuario);
        carritoRepository.save(carritoCompra);
        return carritoCompra.getIdCarrito();
    }

    public CarritoCompra buscarCarrito(String id) {
        return carritoRepository.findCarritoCompraByIdCarrito(id);
    }

    @Transactional
    public void agregar(String idLibro,String idCarrito) {
        CarritoCompra carritoCompra = carritoRepository.findCarritoCompraByIdCarrito(idCarrito);
        carritoCompra.getLibros().add(idLibro);
    }

    @Transactional
    public void eliminar(String idLibro,String idCarrito) {
        CarritoCompra carritoCompra = carritoRepository.findCarritoCompraByIdCarrito(idCarrito);
        carritoCompra.getLibros().remove(idLibro);
    }

    public CarritoCompra buscarCarritoByUsuario(String id) {
        return carritoRepository.findCarritoCompraByIdCarrito(id);
    }
}
