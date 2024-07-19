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

    public String crearCarrito(int idUsuario) {
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
        System.out.println(carritoCompra.getIdCarrito());
        System.out.println(carritoCompra.getLibros().toString());
        carritoCompra.getLibros().add(idLibro);
    }

    @Transactional
    public void eliminar(String idLibro,String idCarrito) {
        CarritoCompra carritoCompra = carritoRepository.findCarritoCompraByIdCarrito(idCarrito);
        carritoCompra.getLibros().remove(idLibro);
    }

    public CarritoCompra buscarCarritoByUsuario(int id) {
        return carritoRepository.findCarritoCompraByIdUsuario(id);
    }

    @Transactional
    public void limpiar(String idCarrito) {
        CarritoCompra carritoCompra = buscarCarrito(idCarrito);
        if (carritoCompra != null) {
            carritoRepository.delete(carritoCompra);
        }
//        carritoCompra.getLibros().clear();
    }
}
