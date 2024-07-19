package com.grupo5.microserviciocarritocompras.repositorios;

import com.grupo5.microserviciocarritocompras.entidades.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository  extends JpaRepository<CarritoCompra,String> {
    CarritoCompra findCarritoCompraByIdCarrito(String id);
    CarritoCompra findCarritoCompraByIdUsuario(int id);
}
